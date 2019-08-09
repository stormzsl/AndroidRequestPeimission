package com.wly.maven;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wly.permission.annotation.Permission;
import com.wly.permission.annotation.PermissionCancel;
import com.wly.permission.annotation.PermissionDeny;

import androidx.annotation.Nullable;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        requestAllPermissions();

    }

    @Permission({android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION})
    private void requestAllPermissions() {
        Toast.makeText(this, "权限申请通过",Toast.LENGTH_LONG).show();
    }

    @PermissionCancel
    private void permissionCancel() {
        Toast.makeText(this, "权限申请被取消",Toast.LENGTH_LONG).show();
    }

    @PermissionDeny
    private void permissionDeny() {
        Toast.makeText(this, "权限申请被拒绝",Toast.LENGTH_LONG).show();
    }
}
