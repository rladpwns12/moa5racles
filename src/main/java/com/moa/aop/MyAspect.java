package com.moa.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class MyAspect {
    private static final Log Logger= LogFactory.getLog(MyAspect.class);
    @Pointcut("execution(* com.moa.model..controller.*.*(..))")//작동 되는 시점
    public void testPointcut(){}
    @Around("testPointcut()")
    public Object checkTime(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.nanoTime();
        // before excution에서 실행될 메서드
        HttpServletRequest request=
                ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        Object retVal= pjp.proceed(); // 홀딩된 원래의 메서드 실행
        long endTime=System.nanoTime();

        return retVal;
    }
}
