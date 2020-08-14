package com.hellokoding.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* commandLineRunner(..))")
    private void commandLineRunner() {}

    //@Around("@annotation(LogExecutionTime)")
    //@Around("execution(* commandLineRunner(..))")
    @Around("commandLineRunner()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.printf("%s executed in %sms %s",
            joinPoint.getSignature(), executionTime, System.lineSeparator());

        return proceed;
    }
}
