package com.reactlibrary.rns;

/**
 * Name:
 * <p>
 * 2019/7/19 by StoneWay
 * <p>
 * Outline:
 */
//                    {code: int, msg: string, resp: string}
//                    code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
//                    msg: 扫码结果描述（非扫码结果）
//                    resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
public interface ScanResultCallBack {
    void onScanSuccess(int code, String msg, String resp);
}
