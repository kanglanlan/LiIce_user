package com.meida.utils;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import java.util.Map;


/**
 * Created by 小卷毛 on 2016/5/16 0016.
 * <p>
 * 调用支付宝支付startPay（）方法
 */
public class PayTool {

    private static PayTool instance;


    public static PayTool getInstance() {
        if (instance == null) {
            instance = new PayTool();
        }
        return instance;
    }

    public void startPay(final Activity context,final PayCallback callback,final String info) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    PayTask alipay = new PayTask(context);
                    Map<String, String> result = alipay.payV2(info, true);
                    callback.doWork(result.get("resultStatus"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }



    public interface PayCallback {
        public void doWork(String payResult);
    }

}
