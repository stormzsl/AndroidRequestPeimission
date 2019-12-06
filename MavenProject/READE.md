# 1.上传aar到JCenter仓库中
  (1)修改androidPermission/build.gradle中的版本号
  (2)执行 ./gradlew :androidPermission:bintrayUpload即可.
  
  基于aop的动态权限库使用方法:
  (1)在工程最外层的build.gradle中添加:
  ```groovy
  classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0'
```
  (2)在app工程目录中的build.gradle中添加:
  ```groovy
apply plugin: 'android-aspectjx'

implementation 'com.fasten:androidPermission:1.0.3'

```