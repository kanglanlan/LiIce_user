package com.meida.liice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.meida.utils.TimeCount;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePaypswActivity extends BaseActivity {
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.et_zhuce_yzm)
    EditText etZhuceYzm;
    @Bind(R.id.tv_getyzm)
    TextView tvGetyzm;
    private TimeCount time;
    public static Activity changepay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_paypsw);
        ButterKnife.bind(this);
        changeTitle("修改支付密码");
        changepay = this;
        String mobile = PreferencesUtils.getString(baseContext, "user_tel");
        tvPhone.setText(mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()));
    }
    @OnClick({R.id.tv_getyzm, R.id.bt_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getyzm:
                Nonce.getcode(PreferencesUtils.getString(baseContext, "user_tel"), 1, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        time = new TimeCount(60 * 1000, 1000, tvGetyzm, ChangePaypswActivity.this, 1);// 构造CountDownTimer对象
                        time.start();
                    }
                });
                break;
            case R.id.bt_zhuce:
                Nonce.checkcode(PreferencesUtils.getString(baseContext, "user_tel"),etZhuceYzm.getText().toString(),
                        1, baseContext, new Nonce.StringCallback() {
                            @Override
                            public void doWork(String string) {
                                StartActivity(ZhiFuMiMA1Activity.class);
                            }
                        });

                break;
        }
    }
}
