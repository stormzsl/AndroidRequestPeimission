package com.fasten.permission.annotation;

import com.fasten.permission.PermissionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限被拒绝
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionDeny {
    int requestCode() default PermissionUtils.DEFAULT_REQUEST_CODE;
}