package com.ljq.project.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.project.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
@Aspect
@Slf4j
@Component
public class SysLogAspect {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    //定义切点(xxx修改成自己的log地址)
    @Pointcut("@annotation(com.ljq.project.aop.Log)")
    public void aopWebLog() {
    }
    //使用环绕通知
    @Around("aopWebLog()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //使用ServletRequestAttributes请求上下文获取方法更多
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        //获取方法的@Log自定义内容
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Log myLog = method.getAnnotation(Log.class);
        //使用数组来获取参数
        Object[] array = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();

        //执行函数前打印日志
        log.info("调用前：{}：{},传递的参数为：{}", className, methodName, mapper.writeValueAsString(array));
        log.info("URL:{}", request.getRequestURL().toString());
        log.info("请求类型：{}", request.getMethod());
        log.info("IP地址：{}", request.getRemoteAddr());
        log.info("IP地址：{}", LogUtils.getIp(request));
        log.info("浏览器：{}", LogUtils.getBrowser(request).equals("Unknown") ? LogUtils.getBrowser(request):"");
        log.info("操作系统：{}", LogUtils.getOS(request).equals("Unknown") ? LogUtils.getOS(request):"");
        log.info("访问时间：{}", simpleDateFormat.format(new Date()));
        log.info("方法注释：{}", myLog.value());

        //调用整个目标函数执行
        Object obj = pjp.proceed();
        //执行函数后打印日志
        log.info("调用后：返回值为：{}", mapper.writeValueAsString(obj));
        log.info("耗时：{}ms", System.currentTimeMillis() - startTime.get());
        return obj;
    }
}
