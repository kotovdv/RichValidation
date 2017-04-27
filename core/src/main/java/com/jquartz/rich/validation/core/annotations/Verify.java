package com.jquartz.rich.validation.core.annotations;


import java.lang.annotation.*;

/**
 * @author Dmitriy Kotov
 */
@Target(ElementType.TYPE)
@Repeatable(Verification.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {

    String value();
}