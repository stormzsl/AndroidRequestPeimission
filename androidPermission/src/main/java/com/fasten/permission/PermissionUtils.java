package com.fasten.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 权限申请辅助类
 * 为了实现高度可复用性，实现和项目业务逻辑的完全解耦,所以这里使用了AOP来处理权限的申请
 */

public class PermissionUtils {

    public static final int DEFAULT_REQUEST_CODE = 0x1876fc;

    public static boolean hasPermission(Context context, String... permissions) {

        for (String permission : permissions) {
            if (!hasSelfPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 检测某个权限是否已经授权；如果已授权则返回true，如果未授权则返回false
     */
    private static boolean hasSelfPermission(Context context, String permission) {
        try {
            return ContextCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException t) {
            return false;
        }
    }

    public static boolean verifyPermission(Context context, int... gantedResults) {

        if (gantedResults == null || gantedResults.length == 0) {
            return false;
        }
        for (int granted : gantedResults) {
            if (granted != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 检查需要给予的权限是否需要显示理由
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            // 这个API主要用于给用户一个申请权限的解释，该方法只有在用户在上一次已经拒绝过你的这个权限申请。
            // 也就是说，用户已经拒绝一次了，你又弹个授权框，你需要给用户一个解释，为什么要授权，则使用该方法。
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }


    /*
     *反射调用执行
     */
    public static void invokeAnnotationMethod(Object object, Class annotationClass) {
        Class<?> clz = object.getClass();
        Method[] methods = clz.getDeclaredMethods();
        if (methods == null) {
            return;
        }
        for (Method method : methods) {
            boolean isHasAnnotation = method.isAnnotationPresent(annotationClass);
            if (isHasAnnotation) {
                method.setAccessible(true);
                try {
                    method.invoke(object);
                    object=null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
