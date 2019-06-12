package com.meida.utils;

import android.content.Context;

import com.meida.model.PayInfo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.meida.share.Datas.APPID;


public class WXPayTools {

    Context context;

    private static WXPayTools instance;

    public static WXPayTools getInstance() {
        if (instance == null) {
            instance = new WXPayTools();
        }
        return instance;
    }

    public void WeixinPay(Context context, PayInfo.DataBean.WechatBean wehat) {
        this.context = context;
        String sign = wehat.getSign();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        // 将该app注册到微信
        msgApi.registerApp(wehat.getAppid());
        PayReq request = new PayReq();
        request.appId = wehat.getAppid();
        APPID = wehat.getAppid();
        request.partnerId = wehat.getPartnerid();
        request.prepayId = wehat.getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr =  wehat.getNoncestr();
        request.timeStamp = Integer.parseInt(wehat.getTimestamp()) + "";
        request.sign = sign;
        msgApi.sendReq(request);
    }

}
