package com.meida.liice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meida.utils.Nonce;
import com.meida.utils.TimeCount;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WangJiMiMa1Activity extends BaseActivity {

    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_yzm)
    EditText edYzm;
    @Bind(R.id.tv_getyzm)
    TextView tvGetyzm;
    private TimeCount time;
public static Activity wangji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mi_ma1);
        ButterKnife.bind(this);
        changeTitle("忘记密码");
        wangji=this;
    }

    @OnClick({R.id.tv_getyzm, R.id.bt_wangjmima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getyzm:
                if (edPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                Nonce.getcode(edPhone.getText().toString(), 12, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        time = new TimeCount(60 * 1000, 1000, tvGetyzm, WangJiMiMa1Activity.this, 1);// 构造CountDownTimer对象
                        time.start();
                    }
                });
                break;
            case R.id.bt_wangjmima:
                if (edPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(edYzm.getText().toString())) {
                    showtoa("请输入验证码");
                    return;
                }
                Nonce.checkcode(edPhone.getText().toString(), edYzm.getText().toString(), 12, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        startActivity(new Intent(baseContext, WangJiMiMa2Activity.class).putExtra("phone", edPhone.getText().toString()));
                    }
                });
                break;
        }
    }
}
