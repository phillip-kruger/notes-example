package com.github.phillipkruger.notes.stopwatch;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.java.Log;

/**
 * Interceptor that will print the execution time of any method annotated with Stopwatch
 * It will use the logger of the class where the method is annotated. So you can control the log verbose with your calling class logger.
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stopwatch
@Interceptor
@Log
public class StopwatchInterceptor implements Serializable {
    
    @AroundInvoke
    public Object logExecutionTime(InvocationContext ic) throws Exception {
        final long start = System.currentTimeMillis();
        
        
        try{
            return ic.proceed();
        }catch(Throwable t){
            throw t;
        }finally{
            final Method method = ic.getMethod();
            final String methodName = method.getName();
            final String className = getClassName(method);
            final Stopwatch stopwatch = ic.getClass().getAnnotation(Stopwatch.class);
            Level level = Level.INFO; // default
            if(stopwatch!=null && stopwatch.level()!=null){
                level = Level.parse(stopwatch.level());
            }
            this.logTime(level,className,methodName,start);
        }
    }
    
    private void logTime(Level level,String className,String methodName,long start){
        final long time = System.currentTimeMillis() - start;
        getCorrectLogger(className).log(level, LOG_FORMAT, new Object[]{className, methodName, time});
    }
    
    private String getClassName(Method method){
        Class<?> declaringClass = method.getDeclaringClass();
        if(declaringClass==null)return EMPTY;
        return declaringClass.getName();
    }
    
    private Logger getCorrectLogger(String className){
        if(className==null || className.isEmpty())return log;
        Logger logger = Logger.getLogger(className);
        if(logger==null)return log;
        return logger;
    }
    
    private static final String LOG_FORMAT = "**** Stopwatch: [{0}.{1}] took [{2} ms] ****";
    private static final String EMPTY = "";
}