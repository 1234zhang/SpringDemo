package com.example.demo.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Order(2)
public class CacheAspect {
    private Gson gson = new Gson();
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void cache(){}
    @Before("cache()")
    public void before(JoinPoint joinPoint){}
    @Around("cache()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object obj = null;
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String name = request.getParameter("name");
        if(redisTemplate.opsForValue().get(name) != null) {
            return gson.fromJson(redisTemplate.opsForValue().get(name), Object.class);
        }
        try {
            obj = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
    @AfterReturning(value = "cache()",returning = "object")
    public void afterReturning(JoinPoint joinPoint,Object object){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        redisTemplate.opsForValue().set(request.getParameter("name"),gson.toJson(object));
        redisTemplate.expire(request.getParameter("name"),2, TimeUnit.MINUTES);
    }

}
