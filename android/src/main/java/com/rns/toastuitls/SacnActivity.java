package com.rns.toastuitls;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rns.toastuitls.qrcodes.QRCodeView;
import com.rns.toastuitls.zxings.ZXingView;

/**
 * Name:
 * <p>
 * 2019/7/12 by StoneWay
 * <p>
 * Outline:
 */
public class SacnActivity extends Activity implements QRCodeView.Delegate {

    ZXingView mZxingview;
    boolean isOpenLighted = false;

    public final static int CHOOSE_REQUEST = 188;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacn);
        mZxingview = findViewById(R.id.zxingview);
        mZxingview.setDelegate(this);
//        mZxingview.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码
//        imgGoback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

//        mLight.setOnClickListener(v -> {
//            if (isOpenLighted) {
//                mZxingview.closeFlashlight();
//                mLight.setImageResource(R.mipmap.icon_light_white);
//            } else {
//                mZxingview.openFlashlight();
//                mLight.setImageResource(R.mipmap.icon_light_red);
//            }
//            isOpenLighted = !isOpenLighted;
//        });

//        mXiangce.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaSelector.create(ScanActivity.this)
//                        .openGallery(MediaMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                        .theme(R.style.MediaAppTheme)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//                        .maxSelectNum(1)// 最大图片选择数量
//                        .minSelectNum(1)// 最小选择数量
//                        .imageSpanCount(4)// 每行显示个数
//                        .selectionMode(MediaConfig.SINGLE)// MediaConfig.MULTIPLE : MediaConfig.SINGLE)// 多选 or 单选
//                        .previewImage(true)// 是否可预览图片
//                        .isCamera(false)// 是否显示拍照按钮
//                        .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
//                        .enableCrop(false)// 是否裁剪
//                        .compress(false)// 是否压缩
//                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                        .isGif(false)// 是否显示gif图片
//                        .openClickSound(false)// 是否开启点击声音
////                        .selectionMedia(selectList)// 是否传入已选图片
////                        .videoMaxSecond(15)
////                        .videoMinSecond(3)
//                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                        .minimumCompressSize(100)// 小于100kb的图片不压缩
////                        .rotateEnabled(true) // 裁剪是否可旋转图片
////                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
//                        .forResult(MediaConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//            }
//        });
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
        if (resultCode == Activity.RESULT_OK && requestCode == CHOOSE_REQUEST) {
//            List<LocalMedia> selectList = MediaSelector.obtainMultipleResult(data);
//            String imgpathstr = selectList.get(0).getPath();
//            Log.e("###lujing:", imgpathstr);
//            mZxingview.decodeQRCode(imgpathstr);
        }
    }

    public interface ScanResultCallBack {
        void onScanSuccess(String codeResult);
    }
}