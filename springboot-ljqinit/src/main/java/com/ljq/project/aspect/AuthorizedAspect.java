package com.ljq.project.aspect;

import javax.servlet.http.HttpServletRequest;

import com.ljq.project.utils.DateTimeUtil;
import com.ljq.project.utils.RedisUtils;
import com.ljq.project.utils.RequestHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>
 * 登 陆验证
 * </p>
 * 
 * @Author wlj
 * @version 2.0
 * @Date 2021/3/9
 */
@Aspect
@Component
@Slf4j
public class AuthorizedAspect {
    @Autowired
    HttpServletRequest request;

    @Autowired
    RequestHelper requestHelper;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 注解的形式 也可以通过切点表达式直接指定需要拦截的package,需要拦截的class以及 ethod 切点表达式: execution(...) 多个 "execution() || execution()"
     */
    @Pointcut("@annotation(com.ljq.project.annotation.Authorized)")
    public void AuthorizedCut() {}

    /**
     * 环绕通知 @Around, 然也可以使用 @Before (前置通知) @After (后置通知)
     * 
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("AuthorizedCut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        return checkTokenAround(point);
    }

    /**
     * 校验token(Head, Authorization), Authorization 配置项 Constants.HEADER_STRING_TOKEN 如成功, 根据GET/POST添加请求参数auth_uid
     *
     * @param point
     * @return
     * @throws Throwable
     */
    private Object checkTokenAround(ProceedingJoinPoint point) throws Throwable {
        log.warn("==== AuthorizedAspect checkTokenAround ====");
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = point.getArgs();
        Long now = DateTimeUtil.getNowSecond(0);

        // Post/Get 参数
        MethodSignature signature = (MethodSignature)point.getSignature();
        String[] para_keys = signature.getParameterNames();
        JSONObject pmap = getParametersMap(para_keys, args);

        JSONObject userToken = requestHelper.userInfo();
        if (userToken == null) {
            return null;
        }
        Integer uid = userToken.getInteger("uid");
        String uuid = userToken.getString("uuid");
        Integer utype = userToken.getInteger("utype");
        Integer ctype = userToken.getInteger("ctype");
        // Integer expire = userToken.getInteger("expire");
        // if (expire > 0 && expire < now) {
        // return JsonResult.error().status(HttpStatus.UNAUTHORIZED).msg("Token Expire").put("data", null);
        // }

        Object result = null;
        if (method.equals("GET")) {
            if (queryString != null) {
                queryString += "&auth_uid=" + uid;
                queryString += "&auth_uuid=" + uuid;
                queryString += "&auth_utype=" + utype;
                queryString += "&auth_ctype=" + ctype;
                result = point.proceed(new Object[] {queryString});
            } else {
                result = point.proceed();
            }
        } else if (method.equals("POST")) {
            // POST接口必须接收Json数据
            // 原始@RequestBody, 原有参数不改变,
            // 增加uid (根据token从数据表sys_login获取uid)
            Object[] paras_raw = point.getArgs();
            JSONObject params_json = null;
            if (paras_raw != null && paras_raw.length > 0) {
                Object request_body = paras_raw[0];
                try {
                    // Json参数 @RequestBody
                    params_json = JSON.parseObject(String.valueOf(request_body));
                    params_json.put("auth_uid", uid);
                    params_json.put("auth_uuid", uuid);
                    params_json.put("auth_utype", utype);
                    params_json.put("auth_ctype", ctype);
                    result = point.proceed(new Object[] {params_json.toJSONString()});
                } catch (Exception ex) {
                    // form数据
                    result = point.proceed(paras_raw);
                }
            } else {
                // 原始无参数 则proceed不传递参数
                // java.lang.IllegalArgumentException: Expecting 0 arguments to proceed, but was passed 1 arguments
                result = point.proceed();
            }
        }

        return result;
    }

    private JSONObject getParametersMap(String[] para_keys, Object[] args) {
        JSONObject pmap = new JSONObject();
        for (int i = 0; i < para_keys.length; i++) {
            String key = para_keys[i];
            pmap.put(key, args[i]);
        }
        return pmap;
    }
}
