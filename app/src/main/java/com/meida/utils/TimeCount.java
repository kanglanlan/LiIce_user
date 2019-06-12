package com.meida.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.meida.liice.R;


/**
 * 作者 亢兰兰
 * 时间 2016/11/30 0030
 * 公司  郑州软盟
 */

public class TimeCount extends CountDownTimer {
    TextView button;
    Context context;
    int type;

    public TimeCount(long millisInFuture, long countDownInterval, TextView bt, Context context, int type) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.button = bt;
        this.context = context;
        this.type = type;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        button.setText("获取验证码");
        button.setClickable(true);
        if (type == 1)//注册
        {
            button.setBackgroundResource(R.drawable.round_gray);
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        button.setClickable(false);
        if (type == 1)//注册
        {
            button.setBackgroundResource(R.drawable.bg_round_blue_15);
        }
        button.setText(millisUntilFinished / 1000 + "s后重发");
    }
}
