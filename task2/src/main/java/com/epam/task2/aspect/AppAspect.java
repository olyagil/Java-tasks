package com.epam.task2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AppAspect {

    @Around("inLogExecutionAnnotation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("Starting processing method: " + joinPoint.getSignature());
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info("Method " + joinPoint.getSignature() + " executed in "
                + executionTime + "ms");
        return proceed;
    }

    @Pointcut("@within(com.epam.task2.annotation.LogExecutionTime)")
    public void inLogExecutionAnnotation() {
    }
}
