package com.meida.liice;

import android.os.Bundle;

import com.jungly.gridpasswordview.GridPasswordView;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.ChangePaypswActivity.changepay;

public class ZhiFuMiMaActivity extends BaseActivity {

    @Bind(R.id.gpv_dialog_withdraw_pwd)
    GridPasswordView gpvDialogWithdrawPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu_mi_ma);
        ButterKnife.bind(this);
        changeTitle("设置支付密码");
    }

    @OnClick(R.id.bt_xiayibu)
    public void onClick() {
        if (!getIntent().getStringExtra("mima").equals(gpvDialogWithdrawPwd.getPassWord().toString())) {
            showtoa("两次密码不一致");
            return;
        }
        mRequest = NoHttp.createStringRequest(HttpIp.set_pass, Const.POST);
        mRequest.add("type", 2);
        mRequest.add("password", gpvDialogWithdrawPwd.getPassWord().toString());
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
                            PreferencesUtils.putString(baseContext, "pay_pass_status", "1");
                            if (changepay != null) {
                                changepay.finish();
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
