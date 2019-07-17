package com.reactlibrary.rns;

import android.annotation.SuppressLint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactlibrary.rns.qrcode.R;
import com.reactlibrary.rns.qrcodes.QRCodeView;
import com.reactlibrary.rns.rnViews.RnQrCodeView;
import com.reactlibrary.rns.zxings.ZXingView;

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
        RnQrCodeView rnQrCodeView = new RnQrCodeView(reactContext);
        zXingView = rnQrCodeView.getZxView();
        zXingView.setDelegate(this);
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
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
        scanResultCallBack.onScanSuccess(result);
        if (zXingView!=null){
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

    public interface ScanResultCallBack {
        void onScanSuccess(String codeResult);
    }

    static boolean isOpenLighted = false;
    public static void swithLightStatue(int lightOn) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LightEventBean event) {
        if (event.getLightOn()==0){
            isOpenLighted=false;
        }else {
            isOpenLighted=true;
        }

        if (isOpenLighted) {
            zXingView.closeFlashlight();
        } else {
            zXingView.openFlashlight();
        }
        isOpenLighted = !isOpenLighted;
    }

}
