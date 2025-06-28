package com.pm.patient_service.aspect;

import com.pm.patient_service.customAnnotation.TimeLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@Slf4j

public class TimeLoggerAspect {
    /**
     * execute point cut for a particular method
     */
    @Pointcut("execution(* com.pm.patient_service.controller.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void beforeExecution(JoinPoint joinPoint){
        log.info("joinPoint signature:{}",joinPoint.getSignature().getName());
        log.info("joinPoint get Kind:{}",joinPoint.getKind());
        log.info("Before timestamp :{}",new Timestamp(System.currentTimeMillis()));
       // log.info("joinPoint get Kind:{}",joinPoint.getSourceLocation().getFileName());
    }

    @After("pointCut()")
    public void afterExecution(JoinPoint joinPoint) {
        log.info("joinPoint signature after execution:{}",joinPoint.getSignature().getName());
        log.info("joinPoint get Kind after execution:{}",joinPoint.getKind());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = LocalDateTime.now().format(formatter);
        log.info("After execution timeStamp: {}", timestamp);
        //log.info("joinPoint get Kind after execution:{}",joinPoint.getSourceLocation().getFileName());

    }

    @Pointcut("@annotation(com.pm.patient_service.customAnnotation.TimeLogger)")
    public void annotationPointCut(){
    }

    @Before("annotationPointCut()")
    public void beforeExecutionCustomAnnotation(JoinPoint joinPoint){
        log.info("time logger before execution:{}",joinPoint.getSignature().getName());
        log.info("time logger get Kind before execution:{}",joinPoint.getKind());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = LocalDateTime.now().format(formatter);
        log.info("time logger before execution timeStamp: {}", timestamp);
    }

    @After("annotationPointCut()")
    public void afterExecutionCustomAnnotation(JoinPoint joinPoint){
        log.info("time logger after execution:{}",joinPoint.getSignature().getName());
        log.info("time logger get Kind after execution:{}",joinPoint.getKind());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = LocalDateTime.now().format(formatter);
        log.info("time logger after execution timeStamp: {}", timestamp);
    }
}
