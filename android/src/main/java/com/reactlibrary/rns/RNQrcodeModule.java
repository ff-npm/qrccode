package com.reactlibrary.rns;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.yanzhenjie.permission.AndPermission;

public class RNQrcodeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNQrcodeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        Log.e("###","RNQrcodeModule初始化");
    }

    @Override
    public String getName() {
        return "RNQRCode";
    }


    /**
     * 吊起二维码扫码控制器
     *
     * @param callback 返回的是一个扫描字符串信息;
     */
    @ReactMethod
    public void nativeQRCodeWithCallback(Callback callback) {
        Log.e("##刚接到事件",System.currentTimeMillis()+"");

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        AndPermission.with(reactContext).runtime().permission(perms)
                .onGranted(permissions -> reactContext.startActivity(new Intent(reactContext, SacnActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)))
                .onDenied(permissions -> Toast.makeText(reactContext, "没有打开相机的权限", Toast.LENGTH_LONG).show())
                .start();
        SacnActivity.getScanResult(new SacnActivity.ScanResultCallBack() {
            @Override
            public void onScanSuccess(String codeResult) {
                callback.invoke(codeResult);
            }
        });

    }

    /**
     * 打开相册选择图片识别二维码
     * @param callback
     */
    @ReactMethod
    public void libraryPhotoQRCodeWithCallback(Callback callback){
        String[] perms = { Manifest.permission.READ_EXTERNAL_STORAGE};
        AndPermission.with(reactContext).runtime().permission(perms)
                .onGranted(permissions -> reactContext.startActivity(new Intent(reactContext, AlbumActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)))
                .onDenied(permissions -> Toast.makeText(reactContext, "没有打开相机的权限", Toast.LENGTH_LONG).show())
                .start();

        AlbumActivity.getScanResult(new AlbumActivity.ScanResultCallBack() {
            @Override
            public void onScanSuccess(String codeResult) {
                callback.invoke(codeResult);
            }
        });
    }



    @ReactMethod
    public void flashSwitch(int lightOn){
        SacnActivity.swithLightStatue(lightOn);
    }


}