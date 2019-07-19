package com.reactlibrary.rns;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nonnull;

/**
 * Name:
 * <p>
 * 2019/7/13 by StoneWay
 * <p>
 * Outline:
 */
public class RNQrViewModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public RNQrViewModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        Log.e("###", "初始化RNQrViewModule");
    }

    @Nonnull
    @Override
    public String getName() {
        return "QrCodeView";
    }


    @ReactMethod
    public void initScanQRCodeWithCallback(Callback callback) {
        RNQrcodeManager.getScanResult(new RNQrcodeManager.ScanResultCallBack() {
            @Override
            public void onScanSuccess(int code, String msg, String resp) {
                //                    {code: int, msg: string, resp: string}
//                    code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
//                    msg: 扫码结果描述（非扫码结果）
//                    resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("code", code);
                writableMap.putString("msg", msg);
                writableMap.putString("resp", resp);
                callback.invoke(writableMap);
            }
        });
//        RNQrcodeManager.getScanResult(new RNQrcodeManager.ScanResultCallBack() {
//            @Override
//            public void onScanSuccess(String codeResult) {
//                callback.invoke(codeResult);
//
//            }
//        });

    }

    @ReactMethod
    public void flashSwitch(int lightOn) {
//        RNQrcodeManager.swithLightStatue(lightOn);
        EventBus.getDefault().post(new LightEventBean(lightOn));
    }
}
