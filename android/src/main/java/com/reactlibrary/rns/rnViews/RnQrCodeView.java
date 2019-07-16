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
//public class RnQrCodeView extends QRCodeView {
//    private MultiFormatReader mMultiFormatReader;
//    private Map<DecodeHintType, Object> mHintMap;
//
//
//    public RnQrCodeView(Context context, AttributeSet attributeSet) {
//        this(context, attributeSet, 0);
//    }
//
//    public RnQrCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//
//    @Override
//    protected void setupReader() {
//        mMultiFormatReader = new MultiFormatReader();
//
//        if (mBarcodeType == BarcodeType.ONE_DIMENSION) {
//            mMultiFormatReader.setHints(QRCodeDecoder.ONE_DIMENSION_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.TWO_DIMENSION) {
//            mMultiFormatReader.setHints(QRCodeDecoder.TWO_DIMENSION_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.ONLY_QR_CODE) {
//            mMultiFormatReader.setHints(QRCodeDecoder.QR_CODE_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.ONLY_CODE_128) {
//            mMultiFormatReader.setHints(QRCodeDecoder.CODE_128_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.ONLY_EAN_13) {
//            mMultiFormatReader.setHints(QRCodeDecoder.EAN_13_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.HIGH_FREQUENCY) {
//            mMultiFormatReader.setHints(QRCodeDecoder.HIGH_FREQUENCY_HINT_MAP);
//        } else if (mBarcodeType == BarcodeType.CUSTOM) {
//            mMultiFormatReader.setHints(mHintMap);
//        } else {
//            mMultiFormatReader.setHints(QRCodeDecoder.ALL_HINT_MAP);
//        }
//    }
//
//    /**
//     * 设置识别的格式
//     *
//     * @param barcodeType 识别的格式
//     * @param hintMap     barcodeType 为 BarcodeType.CUSTOM 时，必须指定该值
//     */
//    public void setType(BarcodeType barcodeType, Map<DecodeHintType, Object> hintMap) {
//        mBarcodeType = barcodeType;
//        mHintMap = hintMap;
//
//        if (mBarcodeType == BarcodeType.CUSTOM && (mHintMap == null || mHintMap.isEmpty())) {
//            throw new RuntimeException("barcodeType 为 BarcodeType.CUSTOM 时 hintMap 不能为空");
//        }
//        setupReader();
//    }
//
//    @Override
//    protected ScanResult processBitmapData(Bitmap bitmap) {
//        return new ScanResult(QRCodeDecoder.syncDecodeQRCode(bitmap));
//    }
//
//    @Override
//    protected ScanResult processData(byte[] data, int width, int height, boolean isRetry) {
//        Result rawResult = null;
//        Rect scanBoxAreaRect = null;
//
//        try {
//            PlanarYUVLuminanceSource source;
//            scanBoxAreaRect = mScanBoxView.getScanBoxAreaRect(height);
//            if (scanBoxAreaRect != null) {
//                source = new PlanarYUVLuminanceSource(data, width, height, scanBoxAreaRect.left, scanBoxAreaRect.top, scanBoxAreaRect.width(),
//                        scanBoxAreaRect.height(), false);
//            } else {
//                source = new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false);
//            }
//
//            rawResult = mMultiFormatReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(source)));
//            if (rawResult == null) {
//                rawResult = mMultiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));
//                if (rawResult != null) {
//                    WstQRCodeUtils.d("GlobalHistogramBinarizer 没识别到，HybridBinarizer 能识别到");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            mMultiFormatReader.reset();
//        }
//
//        if (rawResult == null) {
//            return null;
//        }
//
//        String result = rawResult.getText();
//        if (TextUtils.isEmpty(result)) {
//            return null;
//        }
//
//        BarcodeFormat barcodeFormat = rawResult.getBarcodeFormat();
//        WstQRCodeUtils.d("格式为：" + barcodeFormat.name());
//
//        // 处理自动缩放和定位点
//        boolean isNeedAutoZoom = isNeedAutoZoom(barcodeFormat);
//        if (isShowLocationPoint() || isNeedAutoZoom) {
//            ResultPoint[] resultPoints = rawResult.getResultPoints();
//            final PointF[] pointArr = new PointF[resultPoints.length];
//            int pointIndex = 0;
//            for (ResultPoint resultPoint : resultPoints) {
//                pointArr[pointIndex] = new PointF(resultPoint.getX(), resultPoint.getY());
//                pointIndex++;
//            }
//
//            if (transformToViewCoordinates(pointArr, scanBoxAreaRect, isNeedAutoZoom, result)) {
//                return null;
//            }
//        }
//        return new ScanResult(result);
//    }
//
//    private boolean isNeedAutoZoom(BarcodeFormat barcodeFormat) {
//        return isAutoZoom() && barcodeFormat == BarcodeFormat.QR_CODE;
//    }
//}