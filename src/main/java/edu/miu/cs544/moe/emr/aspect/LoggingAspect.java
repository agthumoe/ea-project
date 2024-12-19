package edu.miu.cs544.moe.emr.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Pointcut("execution(* edu.miu.cs544.moe.emr.domain.*.*Service.*(..))")
    public void serviceLogging() {}

    @Around("serviceLogging()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        // log method name and arguments
        logger.info("Before Class: {}, Method: {}, with args: {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
        var object = joinPoint.proceed();
        logger.info("After Class: {}, Method: {}, with result: {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), object);
        return object;
    }
}
