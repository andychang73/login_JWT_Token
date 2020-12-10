package com.example.login_JWT_Token.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution (* com.example.login_JWT_Token.controllers.UserController.*(..))")
    public void k(){};

    @Before("k()")
    public void myAdvice(JoinPoint jp){
        log.info("Method Signature: {}", jp.getSignature());
    }

    @Pointcut("execution (* com.example.login_JWT_Token.controllers.AuthenticationController.*(..))")
    public void a(){};

    @Around("a()")
    public Object printAround(ProceedingJoinPoint jp) throws Throwable {
        log.info("AOP around before calling {}", jp.getTarget());
        Object obj = jp.proceed();
        log.info("AOP around after calling {}", jp.getTarget());
        return obj;
    }
}
