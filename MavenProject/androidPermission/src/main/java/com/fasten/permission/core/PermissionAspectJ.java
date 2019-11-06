package com.fasten.permission.core;

import android.content.Context;
import android.util.Log;

import com.fasten.permission.PermissionActivity;
import com.fasten.permission.PermissionUtils;
import com.fasten.permission.annotation.Permission;
import com.fasten.permission.annotation.PermissionCancel;
import com.fasten.permission.annotation.PermissionDeny;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.ref.WeakReference;

import androidx.fragment.app.Fragment;

/**
 * 针对项目所使用的切入点
 */

@Aspect
public class PermissionAspectJ {

    //针对所有方法，不限参数并且当执行的方法上拥有指定的permission注解时生效。
    @Pointcut("execution(@com.fasten.permission.annotation.Permission * *(..)) && @annotation(permission)")
    public void requestPermission(Permission permission) {

    }

    /*
     *joinPoint提供了很多方法包括方法签名等信息可以获取
     */
    @Around("requestPermission(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permission permission) {
        Log.e("stormzsl","00000");
        Context context = null;
        final Object objectActivity = joinPoint.getThis();
        if (joinPoint.getThis() instanceof Context) {
            context = (Context) objectActivity;
        } else if (joinPoint.getThis() instanceof Fragment) {
            context = ((Fragment) objectActivity).getActivity();
        } else if (joinPoint.getThis() instanceof android.app.Fragment) {
            context = ((android.app.Fragment) objectActivity).getActivity();
        }
        if (context == null || permission == null) {
            return;
        }
        Log.e("stormzsl","111111");
        PermissionActivity.requestPermission(context, permission.value(), permission.requestCode(), new ActivityPermissionListener(joinPoint));
    }

    private static class ActivityPermissionListener implements PermissionListener {

        private ProceedingJoinPoint ProceedingJoinPoint;
        private WeakReference<Object> mObjectWeakReference;

        public ActivityPermissionListener(ProceedingJoinPoint point) {
            this.ProceedingJoinPoint = point;
            this.mObjectWeakReference = new WeakReference<>(point.getThis());
        }


        @Override
        public void grantedPermission() {
            try {
                if (ProceedingJoinPoint != null) {
                    this.ProceedingJoinPoint.proceed();
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        @Override
        public void cancelPermission() {
            if (mObjectWeakReference != null && mObjectWeakReference.get() != null) {
                PermissionUtils.invokeAnnotationMethod(mObjectWeakReference.get(), PermissionCancel.class);
            }
        }

        @Override
        public void deniedPermission() {
            if (mObjectWeakReference != null && mObjectWeakReference.get() != null) {
                PermissionUtils.invokeAnnotationMethod(mObjectWeakReference.get(), PermissionDeny.class);
            }
        }
    }

}
