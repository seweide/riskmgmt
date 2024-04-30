package com.haier.hairy.rmp.aspect;

import com.haier.hairy.common.logging.Trace;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class TaskAspect {

    @Around("execution(public * com.haier.hairy.rmp.schedule.*.*(..))")
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        startTrace();
        try {
            return joinPoint.proceed();
        } finally {
            stopTrace();
        }

    }

    private void startTrace() {
        if (StringUtils.isEmpty(Trace.getTraceId())) {
            Trace.start();
        }
    }

    private void stopTrace() {
        if (StringUtils.isNotEmpty(Trace.getTraceId())) {
            Trace.stop();
        }
    }
}