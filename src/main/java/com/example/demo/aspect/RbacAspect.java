package com.example.demo.aspect;

import com.example.demo.been.Role;
import com.example.demo.been.User;
import com.example.demo.service.serviceImp.UserServiceImp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(1)
public class RbacAspect {
    private final UserServiceImp serviceImp;
    @Autowired
    public RbacAspect(UserServiceImp serviceImp){
        this.serviceImp = serviceImp;
    }
    @Pointcut("execution(public * com.example.demo.controller.TestController.*(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
    }
    @After("pointcut()")
    public void after(){
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Map<String, List<Role>> roles = serviceImp.getRoles(request.getParameter("name"));
        User user = serviceImp.getUser(request.getParameter("name"));
        if(roles != null) {
            for (Role role : roles.get(user.getName())) {
                if (role.getRoleName().equals("admin"))
                    return proceedingJoinPoint.proceed();
            }
        }
        return "权限不够";
    }
}
