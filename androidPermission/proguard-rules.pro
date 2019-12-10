# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#-keepattributes *Annotation*

-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }

# 不混淆使用了注解的类及类成员
-keep @com.fasten.permission.annotation.Permission class * {*;}
-keep @com.fasten.permission.annotation.PermissionCancel class * {*;}
-keep @com.fasten.permission.annotation.PermissionDeny class * {*;}
# 如果类中有使用了注解的方法，则不混淆类和类成员
-keepclasseswithmembers class * {
    @com.fasten.permission.annotation.Permission <methods>;
    @com.fasten.permission.annotation.PermissionCancel <methods>;
    @com.fasten.permission.annotation.PermissionDeny <methods>;
}
# 如果类中有使用了注解的字段，则不混淆类和类成员
-keepclasseswithmembers class * {
        @com.fasten.permission.annotation.Permission <fields>;
        @com.fasten.permission.annotation.PermissionCancel <fields>;
        @com.fasten.permission.annotation.PermissionDeny <fields>;
}
 -keep class com.fasten.permission.JPPermissionActivity {*;}

