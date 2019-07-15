package com.reactlibrary.rns;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.reactlibrary.rns.almns.RealPathFromUriUtils;
import com.reactlibrary.rns.qrcodes.QRCodeView;
import com.reactlibrary.rns.qrcode.R;
import com.reactlibrary.rns.zxings.ZXingView;
import com.yanzhenjie.permission.AndPermission;

/**
 * Name:
 * <p>
 * 2019/7/12 by StoneWay
 * <p>
 * Outline:
 */
public class SacnActivity extends Activity implements QRCodeView.Delegate {

    ZXingView mZxingview;
    ImageView mGoBack,mAlbum,mLight;
    boolean isOpenLighted = false;

    public final static int CHOOSE_REQUEST = 188;
    private static final int CODE_GALLERY_REQUEST = 0xa0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacn);
        mZxingview = (ZXingView) findViewById(R.id.zxingview);
        mZxingview.setDelegate(this);
//        mZxingview.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码

        mGoBack = (ImageView) findViewById(R.id.toolbar_go_back);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAlbum= (ImageView) findViewById(R.id.toolbar_menu_img);
        mAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
                AndPermission.with(SacnActivity.this).runtime().permission(perms)
                        .onGranted(permissions ->  scanAlbumQr())
                        .onDenied(permissions -> Toast.makeText(SacnActivity.this, "没有打开相册的权限", Toast.LENGTH_LONG).show())
                        .start();
            }
        });
        mLight= (ImageView) findViewById(R.id.light);
        mLight.setOnClickListener(v -> {
            if (isOpenLighted) {
                mZxingview.closeFlashlight();
                mLight.setImageResource(R.mipmap.icon_light_white);
            } else {
                mZxingview.openFlashlight();
                mLight.setImageResource(R.mipmap.icon_light_red);
            }
            isOpenLighted = !isOpenLighted;
        });
    }

    private void scanAlbumQr() {
        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mZxingview.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        mZxingview.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZxingview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZxingview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        mZxingview.startSpot();
        if (result == null) {
            result = "";
        }
        Log.e("###", "扫描结果" + result);
        scanResultCallBack.onScanSuccess(result);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = mZxingview.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZxingview.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZxingview.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(SacnActivity.this, "打开相机失败", Toast.LENGTH_LONG).show();
        Log.e("##", "打开相机失败");
        finish();
    }


    public static ScanResultCallBack scanResultCallBack;

    public static void getScanResult(ScanResultCallBack scanResultCallBack) {
        SacnActivity.scanResultCallBack = scanResultCallBack;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mZxingview.startSpotAndShowRect(); // 显示扫描框，并开始识别
        if (resultCode == Activity.RESULT_OK && requestCode == CODE_GALLERY_REQUEST) {
            String imgpathstr = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
            Log.e("###imgpathstr",imgpathstr);
            mZxingview.decodeQRCode(imgpathstr);
        }
    }

    public interface ScanResultCallBack {
        void onScanSuccess(String codeResult);
    }
}