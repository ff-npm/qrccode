package com.ffomall.rns;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.ffomall.rns.almns.RealPathFromUriUtils;
import com.ffomall.rns.qrcode.R;
import com.ffomall.rns.qrcodes.QRCodeView;
import com.ffomall.rns.zxings.ZingView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Name:
 * <p>
 * 2019/7/15 by StoneWay
 * <p>
 * Outline:
 */
public class AlbumActivity extends Activity implements QRCodeView.Delegate {
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    ZingView mZxingview;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XmlPullParser parser = getResources().getXml(R.layout.zxingview_layout);
        AttributeSet attributes = Xml.asAttributeSet(parser);
        int type;
        while (true) {
            try {
                if (!((type = parser.next()) != XmlPullParser.START_TAG &&
                                type != XmlPullParser.END_DOCUMENT)) break;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Empty
        }

//        TextView tv = new TextView(this, attributes);
        mZxingview=new ZingView(this,attributes);
//        setContentView(R.layout.activity_album);
//        mZxingview = (ZXingView) findViewById(R.id.zxingview);
        mZxingview.setDelegate(this);
        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("###fanhui",requestCode+"requestCode"+resultCode+"requestCode"+data+"data");
        mZxingview.startSpotAndShowRect(); // 显示扫描框，并开始识别
        if (resultCode == Activity.RESULT_OK && requestCode == CODE_GALLERY_REQUEST) {
            String imgpathstr = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
            Log.e("###imgpathstr", imgpathstr);
            mZxingview.decodeQRCode(imgpathstr);
        }else if (resultCode==0){
            AlbumActivity.this.finish();
        }
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
        scanResultCallBack.onScanSuccess(200, "SUCCESS", result);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(AlbumActivity.this, "打开相机失败", Toast.LENGTH_LONG).show();
        Log.e("##", "打开相机失败");
        finish();
    }

    public static ScanResultCallBack scanResultCallBack;

    public static void getScanResult(ScanResultCallBack scanResultCallBack) {
        AlbumActivity.scanResultCallBack = scanResultCallBack;
    }

}
