package com.library.library.Tools;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(* com.library.library.Controller.*.*(..))")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Entering " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.library.library.Controller.*.*(..))", returning = "result")
    public void afterReturningControllerMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting " + joinPoint.getSignature().toShortString() + ". Returned: " + result);
    }

    @Before("execution(* com.library.library.Service.*.*(..))")
    public void beforeServiceMethod(JoinPoint joinPoint) {
        logger.info("Entering " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.library.library.Service.*.*(..))", returning = "result")
    public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting " + joinPoint.getSignature().toShortString() + ". Returned: " + result);
    }
}