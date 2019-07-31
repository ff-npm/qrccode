package com.ffomall.rns.qrcodes;

import android.graphics.PointF;

/**
 * Name:
 * <p>
 * 2019/7/12 by StoneWay
 * <p>
 * Outline:
 */
public class ScanResult {
    String result;
    PointF[] resultPoints;

    public ScanResult(String result) {
        this.result = result;
    }

    public ScanResult(String result, PointF[] resultPoints) {
        this.result = result;
        this.resultPoints = resultPoints;
    }
}
