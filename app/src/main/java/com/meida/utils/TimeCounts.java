package com.meida.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者 亢兰兰
 * 时间 2017/11/21 0021
 * 公司  郑州软盟
 */

public class TimeCounts extends CountDownTimer {
    TextView button;
    Button tv;
    Context context;

    public TimeCounts(long millisInFuture, long countDownInterval, TextView bt, Context context, Button tv) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.button = bt;
        this.tv = tv;
        this.context = context;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        button.setText("Remaining payment time：" + "00:00");
        tv.setClickable(false);
//        tv.setBackgroundResource(R.drawable.huibt);
//        tv.setTextColor(context.getResources().getColor(R.color.txthui2));
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String time = format.format(new Date(millisUntilFinished));
        button.setText("Remaining payment time：" + time);
    }
}
