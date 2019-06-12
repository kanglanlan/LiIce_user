package com.meida.liice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meida.model.Login;
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

import static com.meida.liice.ShouYeActivity.shouyeactivity;

public class BangDingPhoneActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.tv_yzm)
    TextView tvYzm;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_ding_phone);
        ButterKnife.bind(this);
        changeTitle("绑定手机号");
    }

    @OnClick({R.id.tv_yzm, R.id.bt_bd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yzm:
                if (etPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                Nonce.getcode(etPhone.getText().toString(), 1, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        time = new TimeCount(60 * 1000, 1000, tvYzm, BangDingPhoneActivity.this, 1);// 构造CountDownTimer对象
                        time.start();
                    }
                });
                break;
            case R.id.bt_bd:
                if (etPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(etYzm.getText().toString())) {
                    showtoa("请输入验证码");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.register, Const.POST);
                /**
                 * type	string	是	1手机2微信3QQ4微博		1
                 user_tel	string	是	请输入正确的手机号		13783451041
                 verify_code	string	是	请输入验证码		201511
                 user_pass	string	是	请输入密码		123456
                 union_id	string	是	第三方登录union_id		e10adc3949ba59abbe56e057f20f883e
                 */
                mRequest.add("type", getIntent().getStringExtra("type"));
                mRequest.add("user_tel", etPhone.getText().toString());
                mRequest.add("verify_code", etYzm.getText().toString());
                mRequest.add("union_id", getIntent().getStringExtra("open_key"));
                mRequest.add("logo", getIntent().getStringExtra("user_logo"));
                mRequest.add("nickname", getIntent().getStringExtra("user_nickname"));
                getRequest(new CustomHttpListener<Login>(baseContext, true, Login.class) {
                    @Override
                    public void doWork(Login data, String code) {
                        if ("1".equals(data.getCode())) {
                            Nonce.putinfo(baseContext, data);
                        }
                    }

                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
//                        2 该手机号尚未注册 3密码不正确 4用户被禁用 5请注册绑定
                                showtoa(obj.getString("msg"));
                                if ("1".equals(obj.getString("code"))) {
                                    PreferencesUtils.putInt(baseContext, "login", 1);
                                    if (shouyeactivity != null) {
                                        shouyeactivity.finish();
                                    }
                                    StartActivity(ZhuCeOkActivity.class);
                                    finish();
                                }
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
