package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;

import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiFuMiMA1Activity extends BaseActivity {

    @Bind(R.id.gpv_dialog_withdraw_pwd)
    GridPasswordView gpvDialogWithdrawPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu_mi_ma1);
        ButterKnife.bind(this);
        changeTitle("设置支付密码");
    }

    @OnClick(R.id.bt_xiayibu)
    public void onClick() {
        if (gpvDialogWithdrawPwd.getPassWord().toString().length() != 6) {
            showtoa("请输入6位密码");
            return;
        }
        startActivity(new Intent(baseContext, ZhiFuMiMaActivity.class)
                .putExtra("mima", gpvDialogWithdrawPwd.getPassWord().toString() ));
        finish();
    }
}
