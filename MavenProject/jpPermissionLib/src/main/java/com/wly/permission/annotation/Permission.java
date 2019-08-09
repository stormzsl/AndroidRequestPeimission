package com.wly.permission.annotation;

import com.wly.permission.PermissionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    String [] value();

    int requestCode() default PermissionUtils.DEFAULT_REQUEST_CODE;
}
