package com.reactlibrary.rns;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactlibrary.rns.qrcode.R;
import com.reactlibrary.rns.qrcodes.QRCodeView;
import com.reactlibrary.rns.rnViews.RnQrCodeView;
import com.reactlibrary.rns.zxings.ZXingView;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * Name:
 * <p>
 * 2019/7/13 by StoneWay
 * <p>
 * Outline:
 */
public class RNQrcodeManager extends SimpleViewManager<RnQrCodeView> implements QRCodeView.Delegate {

    ThemedReactContext reactContext;
    ZXingView zXingView;
    RnQrCodeView rnQrCodeView;


    @Nonnull
    @Override
    public String getName() {
        return "QrCodeView";
    }

    @SuppressLint("NewApi")
    @Nonnull
    @Override
    protected RnQrCodeView createViewInstance(@Nonnull ThemedReactContext reactContext) {
        this.reactContext = reactContext;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        String[] perms = {Manifest.permission.CAMERA};
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        AndPermission.with(reactContext).runtime().permission(perms)
                .onGranted(permissions -> {
                    rnQrCodeView = new RnQrCodeView(reactContext);
                    zXingView = rnQrCodeView.getZxView();
                    zXingView.setDelegate(this);
                    zXingView.startCamera();
                    zXingView.startSpotAndShowRect();
                })
                .onDenied(permissions -> {
//                    {code: int, msg: string, resp: string}
//                    code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
//                    msg: 扫码结果描述（非扫码结果）
//                    resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
                    scanResultCallBack.onScanSuccess(202,"NO_CAMERA","");
                })
                .start();


        return rnQrCodeView;
    }

    @SuppressLint("ResourceType")
    private AttributeSet getAttrs() {
        XmlPullParser parser = reactContext.getResources().getXml(R.layout.zxingview_layout);
        AttributeSet attributes = Xml.asAttributeSet(parser);
        int type;
        while (true) {
            try {
                if (!((type = parser.next()) != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT))
                    break;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return attributes;

    }

    @Override
    public void onDropViewInstance(@Nonnull RnQrCodeView view) {
        view.getZxView().closeFlashlight();
        view.getZxView().onDestroy(); // 销毁二维码扫描控件
        EventBus.getDefault().unregister(this);
        super.onDropViewInstance(view);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //                    {code: int, msg: string, resp: string}
//                    code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
//                    msg: 扫码结果描述（非扫码结果）
//                    resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
        scanResultCallBack.onScanSuccess(200, "SUCCESS", result);
        if (zXingView != null) {
            zXingView.startSpot();
        }
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
    }


    public static ScanResultCallBack scanResultCallBack;

    public static void getScanResult(ScanResultCallBack scanResultCallBack) {
        RNQrcodeManager.scanResultCallBack = scanResultCallBack;
    }

    //                    {code: int, msg: string, resp: string}
//                    code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
//                    msg: 扫码结果描述（非扫码结果）
//                    resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
    public interface ScanResultCallBack {
        void onScanSuccess(int code, String msg, String resp);
    }

    static boolean isOpenLighted = false;

    public static void swithLightStatue(int lightOn) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LightEventBean event) {
        if (event.getLightOn() == 0) {
            isOpenLighted = false;
        } else {
            isOpenLighted = true;
        }

        if (isOpenLighted) {
            zXingView.closeFlashlight();
        } else {
            zXingView.openFlashlight();
        }
        isOpenLighted = !isOpenLighted;
    }

}
