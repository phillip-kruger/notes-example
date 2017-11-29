package com.github.phillipkruger.notes.stopwatch;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

/**
 * Annotation that can indicate that we need to measure the execution time of a method.
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Qualifier
public @interface Stopwatch {
    
    @Nonbinding String level() default "INFO";
    
}
