package com.meida.liice;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.WangJiMiMa1Activity.wangji;

public class WangJiMiMa2Activity extends BaseActivity {

    @Bind(R.id.ed_mima)
    EditText edMima;
    @Bind(R.id.img_eye)
    ToggleButton imgEye;
    @Bind(R.id.ed_zaicimima)
    EditText edZaicimima;
    @Bind(R.id.img_eye2)
    ToggleButton imgEye2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mi_ma2);
        ButterKnife.bind(this);
        changeTitle("忘记密码");
        imgEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    edMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    edMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        imgEye2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    edZaicimima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    edZaicimima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick(R.id.bt_queding)
    public void onClick() {
        if (TextUtils.isEmpty(edMima.getText().toString())) {
            showtoa("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(edZaicimima.getText().toString())) {
            showtoa("请确认密码");
            return;
        }
        mRequest = NoHttp.createStringRequest(HttpIp.findPassword, Const.POST);
        mRequest.add("user_tel", getIntent().getStringExtra("phone"));
        mRequest.add("password", edMima.getText().toString());
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                finish();
            }
            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
//                        2 该手机号尚未注册 3密码不正确 4用户被禁用 5请注册绑定
                        showtoa(obj.getString("msg"));
                        if ("1".equals(obj.getString("code"))) {
                            if(wangji!=null)
                            {
                                wangji.finish();
                            }
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }
}
