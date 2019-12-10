package com.fasten.permission.core;

import java.io.Serializable;

/**
 * 这里定义事件回调
 */

public interface PermissionListener extends Serializable {

    void grantedPermission();//已经授权

    void cancelPermission();//取消授权

    void deniedPermission();//被拒绝 点击了不再提示
}
