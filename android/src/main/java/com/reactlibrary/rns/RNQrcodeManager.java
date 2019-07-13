package com.reactlibrary.rns;

import android.graphics.Bitmap;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import com.reactlibrary.rns.qrcodes.QRCodeView;
import com.reactlibrary.rns.qrcodes.ScanResult;
import com.reactlibrary.rns.rnViews.RnQrCodeView;

import javax.annotation.Nonnull;

/**
 * Name:
 * <p>
 * 2019/7/13 by StoneWay
 * <p>
 * Outline:
 */
public class RNQrcodeManager extends SimpleViewManager<RnQrCodeView> {
    @Nonnull
    @Override
    public String getName() {
        return  "QrCodeView";
    }

    @Nonnull
    @Override
    protected RnQrCodeView createViewInstance(@Nonnull ThemedReactContext reactContext) {
        return new RnQrCodeView(reactContext);
    }




}
