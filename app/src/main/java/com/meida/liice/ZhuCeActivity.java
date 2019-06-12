package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

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

public class ZhuCeActivity extends BaseActivity {

    @Bind(R.id.et_zhuce_phone)
    EditText etZhucePhone;
    @Bind(R.id.et_zhuce_yzm)
    EditText etZhuceYzm;
    @Bind(R.id.tv_getyzm)
    TextView tvGetyzm;
    @Bind(R.id.et_zhuce_mima)
    EditText etZhuceMima;
    @Bind(R.id.img_eye)
    ToggleButton imgEye;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);
        changeTitle("注册账号");
        imgEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    etZhuceMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    etZhuceMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.tv_getyzm, R.id.ll_xieyi, R.id.bt_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getyzm:
                if (etZhucePhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                Nonce.getcode(etZhucePhone.getText().toString(), 11, baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        time = new TimeCount(60 * 1000, 1000, tvGetyzm, ZhuCeActivity.this, 1);// 构造CountDownTimer对象
                        time.start();
                    }
                });
                break;
            case R.id.ll_xieyi:
                startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "101"));
                break;
            case R.id.bt_zhuce:
                if (etZhucePhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
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
                mRequest.add("type", 1);
                mRequest.add("user_tel", etZhucePhone.getText().toString());
                mRequest.add("verify_code", etZhuceYzm.getText().toString());
                mRequest.add("user_pass", etZhuceMima.getText().toString());
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
