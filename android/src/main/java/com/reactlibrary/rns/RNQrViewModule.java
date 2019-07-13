package com.reactlibrary.rns;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
        Log.e("###","初始化RNQrViewModule");
    }

    @Nonnull
    @Override
    public String getName() {
        return "QrCodeView";
    }


    @ReactMethod
    public void initScanQRCodeWithCallback(Callback callback) {
        callback.invoke("jjjjjj");
    }
}
