package com.meida.liice;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.SettingActivity.settingactivity;

public class ChangemimaActivity extends BaseActivity {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_jiumima)
    EditText etJiumima;
    @Bind(R.id.et_xinmima)
    EditText etXinmima;
    @Bind(R.id.et_etumima)
    EditText etEtumima;
    @Bind(R.id.img_eye)
    ToggleButton imgEye;
    @Bind(R.id.img_eye2)
    ToggleButton imgEye2;
    @Bind(R.id.img_eye3)
    ToggleButton imgEye3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changemima);
        ButterKnife.bind(this);
        changeTitle("修改登录密码");
        tvTitleRight.setText("保存");
        imgEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    etJiumima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    etJiumima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        imgEye2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    etXinmima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    etXinmima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        imgEye3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    etEtumima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    etEtumima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.tv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                if (TextUtils.isEmpty(etJiumima.getText().toString())) {
                    showtoa("请输入旧密码");
                    return;
                }
                if (TextUtils.isEmpty(etXinmima.getText().toString())) {
                    showtoa("请输入新密码");
                    return;
                }
                if (etXinmima.getText().toString().length() < 6) {
                    showtoa("密码至少6位");
                    return;
                }
                if (TextUtils.isEmpty(etEtumima.getText().toString())) {
                    showtoa("请确认密码");
                    return;
                }
                if (!etEtumima.getText().toString().equals(etXinmima.getText().toString())) {
                    showtoa("请保持新密码和确认密码一致");
                    return;
                }

                mRequest = NoHttp.createStringRequest(HttpIp.set_pass, Const.POST);
                mRequest.add("type", 1);
                mRequest.add("password", etXinmima.getText().toString());
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                    }
                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                showtoa(obj.getString("msg"));
                                if ("1".equals(obj.getString("code"))) {
                                    Nonce.dellogin(baseContext);
                                    PreferencesUtils.putInt(baseContext, "login", 0);
                                    StartActivity(LoginActivity.class);
                                    if (settingactivity != null) {
                                        settingactivity.finish();
                                    }
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
