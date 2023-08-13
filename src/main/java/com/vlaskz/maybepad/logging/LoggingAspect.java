package com.vlaskz.maybepad.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.vlaskz.maybepad..*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs(); // Get method arguments

        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(", ");
        }

        if (params.length() > 0) {
            params.setLength(params.length() - 2);  // Remove the last comma and space
        }

        logger.info("Method called: " + methodName + " with parameters [" + params + "]");
    }
}
