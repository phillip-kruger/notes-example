package com.github.phillipkruger.notes.event;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * CDI 1.1
 * 
 * An annotation that will indicate that we should send a notification
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Notify {
    @Nonbinding String value() default ""; 
}