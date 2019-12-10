package com.fasten.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import com.fasten.permission.core.PermissionListener;

/**
 * 这种方式可以实现请求权限不再受activity限制
 * service,广播等等，你所希望的任何位置都可实现
 */

public class PermissionActivity extends Activity {

    private static final String PARAM_PERMISSION = "param_permission";
    private static final String PARAM_REQUEST_CODE = "param_request_code";
    private String[] mPermissions;
    private int mRequestCode;
    private static PermissionListener mPermissionListener;

    /**
     * 请求权限
     */
    public static void requestPermission(Context context, String[] permissions, int requestCode, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PARAM_PERMISSION, permissions);
        bundle.putInt(PARAM_REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jp_permission_layout);

        this.mPermissions = getIntent().getStringArrayExtra(PARAM_PERMISSION);
        this.mRequestCode = getIntent().getIntExtra(PARAM_REQUEST_CODE, -1);
        if (mPermissions == null || mRequestCode < 0 || mPermissionListener == null) {
            this.finish();
            return;
        }

        //检查是否已授权
        if (PermissionUtils.hasPermission(this, mPermissions)) {
            mPermissionListener.grantedPermission();
            finish();
            return;
        }
        ActivityCompat.requestPermissions(this, this.mPermissions, this.mRequestCode);

    }

    /*
     *请求权限结果处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionListener == null) {
            return;
        }
        if (PermissionUtils.verifyPermission(this, grantResults)) {
            //请求权限成功
            mPermissionListener.grantedPermission();
            finish();
            return;
        }

        //用户点击了不再显示
        if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
            mPermissionListener.deniedPermission();
            finish();
            return;
        }
        mPermissionListener.cancelPermission();//用户取消
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPermissionListener = null;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
