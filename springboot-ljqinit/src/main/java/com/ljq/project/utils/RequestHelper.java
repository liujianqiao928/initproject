package com.ljq.project.utils;

import com.alibaba.fastjson.JSONObject;

import com.ljq.project.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <p>
 * 助手函数
 * </p>
 *
 * @author wlj
 * @since 2021/3/9
 */
@Component
@Slf4j
public class RequestHelper {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * getUuid
     * <p>
     * 通过token从redis获取登陆的用户的uuid
     * </p>
     *
     * @return java.lang.Integer
     */
    public String getUuid() {
        String token = request.getHeader(Constants.HEADER_STRING_TOKEN);
        String userInfo = redisUtils.get(Constants.SYS_USER_TOKEN + token);
        if (StringUtils.isEmpty(userInfo)) {
            return null;
        } else {
            JSONObject userObject = JSONObject.parseObject(userInfo);

            return userObject.getString("uuid");
        }
    }

    /**
     * userInfo
     * <p>
     * 通过token从redis获取登陆的用户信息
     * </p>
     *
     * @return com.alibaba.fastjson.JSONObject
     */
    public JSONObject userInfo() {
        String token = request.getHeader(Constants.HEADER_STRING_TOKEN);
        String userInfo = redisUtils.get(Constants.SYS_USER_TOKEN + token);
        if (StringUtils.isEmpty(userInfo)) {
            return null;
        } else {
            JSONObject userObject = JSONObject.parseObject(userInfo);

            return userObject;
        }
    }

    /**
     *
     * @return
     */
    public String getRemoteAddr() {
        String addr = request.getRemoteAddr();
        if (StringUtils.isEmpty(addr)) {
            return null;
        } else {
            return addr;
        }
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headerMap = new ConcurrentHashMap<>();
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String)headers.nextElement();
            String value = request.getHeader(name);
            // log.debug(name+"="+value);
            headerMap.put(name, value);
        }
        return headerMap;
    }

    public String getHeader(String name) {
        String value = request.getHeader(name);
        return value;
    }

}
