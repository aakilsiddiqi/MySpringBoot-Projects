package com.login.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.login.service.*Impl.*(..))",throwing = "exception")
    public void logServiceException(Exception exception){
        LOGGER.error(exception.getMessage());
    }
}
