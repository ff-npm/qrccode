package com.reactlibrary.rns.rnViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.reactlibrary.rns.qrcode.R;
import com.reactlibrary.rns.zxings.ZXingView;

/**
 * Name:
 * <p>
 * 2019/7/13 by StoneWay
 * <p>
 * Outline:
 */
public class RnQrCodeView extends RelativeLayout{

    private  Context mContext;
    ZXingView mZXingView;

    public RnQrCodeView(Context context) {
        this(context,null);
    }

    public RnQrCodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RnQrCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.layout_rn_zxingview, this);
        mZXingView=(ZXingView) findViewById(R.id.zxingview);

    }


    public ZXingView getZxView() {
        return mZXingView;
    }
}