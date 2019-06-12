package com.meida.liice;

import android.os.Bundle;
import android.view.View;

import com.meida.ui.fg05.PersonInfo_A;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.LoginActivity.loginactivity;

public class ZhuCeOkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce_ok);
        ButterKnife.bind(this);
        changeTitle("注册成功");
        if(loginactivity!=null){
            loginactivity.finish();
        }
    }

    @OnClick({R.id.bt_wanshan, R.id.bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wanshan:
                StartActivity(PersonInfo_A.class);
                finish();
                break;
            case R.id.bt_ok:
                finish();
                break;
        }
    }
}
