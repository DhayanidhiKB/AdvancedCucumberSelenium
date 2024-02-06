package com.my.salesforce.sandbox.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Extent {
    String name();

    String description();

    String[] author() default {};

    String[] category() default {};

    String device() default "";
}