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
-keep @com.didi.jp.permission.annotation.Permission class * {*;}
-keep @com.didi.jp.permission.annotation.PermissionCancel class * {*;}
-keep @com.didi.jp.permission.annotation.PermissionDeny class * {*;}
# 如果类中有使用了注解的方法，则不混淆类和类成员
-keepclasseswithmembers class * {
    @com.didi.jp.permission.annotation.Permission <methods>;
    @com.didi.jp.permission.annotation.PermissionCancel <methods>;
    @com.didi.jp.permission.annotation.PermissionDeny <methods>;
}
# 如果类中有使用了注解的字段，则不混淆类和类成员
-keepclasseswithmembers class * {
        @com.didi.jp.permission.annotation.Permission <fields>;
        @com.didi.jp.permission.annotation.PermissionCancel <fields>;
        @com.didi.jp.permission.annotation.PermissionDeny <fields>;
}
 -keep class com.didi.jp.permission.JPPermissionActivity {*;}

