package com.vlaskz.maybepad.logging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.vlaskz.maybepad.core..*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        logger.info("Method called: " + methodName + " with arguments: " + argsToString(args));
    }

    @AfterReturning(pointcut = "execution(* com.vlaskz.maybepad.core..*.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        logger.info("Method " + methodName + " returned: " + result);
    }

    private String argsToString(Object[] args) {
        StringBuilder builder = new StringBuilder();
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 2, builder.length());  // remove trailing comma and space
        }
        return builder.toString();
    }
}
