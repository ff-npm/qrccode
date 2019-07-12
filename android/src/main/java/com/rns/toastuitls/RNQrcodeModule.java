package com.rns.toastuitls;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.yanzhenjie.permission.AndPermission;


/**
 * Name:
 * <p>
 * 2019/7/10 by StoneWay
 * <p>
 * Outline:
 */

public class RNQrcodeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
//    private Activity mActivity;

    public RNQrcodeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
//        this.mActivity=reactContext.getCurrentActivity();

    }

    @Override
    public String getName() {
        return "RNQrcode";
    }



    /**
     * 吊起二维码扫码控制器
     *
     * @param callback 返回的是一个扫描字符串信息;
     */
    @ReactMethod
    public void ScanQRCodeWithCallBack(Callback callback) {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
//        AndPermission.with(mActivity)
////                .runtime()
////                .permission(perms)
////                .onGranted(permissions -> {
////                    // Storage permission are allowed.
////                    startActivity(new Intent(PermissActivity.this, ScanActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
////                    finish();
////                })
////                .onDenied(permissions -> {
////                    // Storage permission are not allowed.
////                    Toast.makeText(mActivity, "没有打开相机的权限", Toast.LENGTH_LONG).show();
////                })
////                .start();
        AndPermission.with(reactContext).runtime().permission(perms)
                .onGranted(permissions->{
                    reactContext.startActivity(new Intent(reactContext,SacnActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                })
                .onDenied(permissions->{
                    Toast.makeText(reactContext, "没有打开相机的权限", Toast.LENGTH_LONG).show();
                })
                .start();

        SacnActivity.getScanResult(new SacnActivity.ScanResultCallBack() {
            @Override
            public void onScanSuccess(String codeResult) {
                callback.invoke(codeResult);
            }
        });

//        mContext.startActivity(new Intent(mContext, PermissActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        ScanActivity.getScanResult(new ScanResultCallBack() {
//            @Override
//            public void onScanSuccess(String imgpath) {
//                callback.invoke(imgpath);
//            }
//        });
    }
}
