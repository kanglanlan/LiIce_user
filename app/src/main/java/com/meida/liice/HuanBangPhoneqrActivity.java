package com.meida.liice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.meida.utils.TimeCount;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.HuanBangPhoneActivity.huanbang;

public class HuanBangPhoneqrActivity extends BaseActivity {

    @Bind(R.id.et_zhuce_yzm)
    EditText et_zhuce_yzm;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_getyzm)
    TextView tvGetyzm;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_bang_phoneqr);
        ButterKnife.bind(this);
        changeTitle("换绑手机");
    }


    @OnClick({R.id.tv_getyzm, R.id.bt_bd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getyzm:
                Nonce.getcode(PreferencesUtils.getString(baseContext, "user_tel"), 1, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        time = new TimeCount(60 * 1000, 1000, tvGetyzm, HuanBangPhoneqrActivity.this, 1);// 构造CountDownTimer对象
                        time.start();
                    }
                });
                break;
            case R.id.bt_bd:
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showtoa("请输入新手机号");
                }
                if (TextUtils.isEmpty(et_zhuce_yzm.getText().toString())) {
                    showtoa("请输入验证码");
                }

                mRequest = NoHttp.createStringRequest(HttpIp.set_info, Const.POST);
                mRequest.add("new_user_tel", etPhone.getText().toString());
                mRequest.add("new_verify_code", et_zhuce_yzm.getText().toString());
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        PreferencesUtils.putString(baseContext,"user_tel",etPhone.getText().toString());
                        if (huanbang != null) {
                            huanbang.finish();
                        }
                        finish();
                    }

                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        if (obj != null) {
                            try {
                                showtoa(obj.getString("msg"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
                break;
        }
    }
}
