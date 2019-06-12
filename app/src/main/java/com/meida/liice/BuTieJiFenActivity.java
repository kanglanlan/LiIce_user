package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meida.model.Login;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuTieJiFenActivity extends BaseActivity {

    @Bind(R.id.tv_jifen)
    TextView tvJifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_tie_ji_fen);
        ButterKnife.bind(this);
        changeTitle("补贴积分");
        tvJifen.setText(PreferencesUtils.getString(baseContext, "total_payment_score"));
    }
    @Override
    protected void onResume() {
        super.onResume();
        Nonce.getinfo(baseContext, new Nonce.LoginCallback() {
            @Override
            public void doWork(Login string) {
                tvJifen.setText(PreferencesUtils.getString(baseContext, "total_payment_score"));
            }
        });
    }

    @OnClick({R.id.tv_tixian, R.id.tv_zhuaneng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tixian:
                StartActivity(JiFenModeActivity.class);
                break;
            case R.id.tv_zhuaneng:
                StartActivity(ZhuanZengJifenActivity.class);
                break;
        }
    }
}
