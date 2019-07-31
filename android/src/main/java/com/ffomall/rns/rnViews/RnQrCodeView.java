package com.ffomall.rns.rnViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ffomall.rns.qrcode.R;
import com.ffomall.rns.zxings.ZingView;

/**
 * Name:
 * <p>
 * 2019/7/13 by StoneWay
 * <p>
 * Outline:
 */
public class RnQrCodeView extends RelativeLayout{

    private  Context mContext;
    ZingView ZingView;

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
        ZingView=(ZingView) findViewById(R.id.zxingview);

    }


    public ZingView getZxView() {
        return ZingView;
    }
}