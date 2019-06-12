package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meida.model.Login;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralTiXianActivity extends BaseActivity {

    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tv_jifen)
    TextView tvJifen;
    @Bind(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void onResume() {
        super.onResume();
        Nonce.getsys(baseContext);
        Nonce.getinfo(baseContext, new Nonce.LoginCallback() {
            @Override
            public void doWork(Login string) {
                tvJifen.setText(PreferencesUtils.getString(baseContext, "score"));//补贴
            }
        });
        if ("1".equals(PreferencesUtils.getString(baseContext, "start_flag"))) {
            tvTime.setText("当前提现日期：" + PreferencesUtils.getString(baseContext, "start_date") + "至" +
                    PreferencesUtils.getString(baseContext, "end_date"));
        } else {
            tvTime.setText("下次提现日期：" + PreferencesUtils.getString(baseContext, "start_date") + "至" +
                    PreferencesUtils.getString(baseContext, "end_date"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_ti_xian);
        ButterKnife.bind(this);
        tvTitleRight.setText("提现规则");
        tvJifen.setText(PreferencesUtils.getString(baseContext, "score"));//补贴
        if ("1".equals(PreferencesUtils.getString(baseContext, "start_flag"))) {
            tvTime.setText("当前提现日期：" + PreferencesUtils.getString(baseContext, "start_date") + "至" +
                    PreferencesUtils.getString(baseContext, "end_date"));
        } else {
            tvTime.setText("下次提现日期：" + PreferencesUtils.getString(baseContext, "start_date") + "至" +
                    PreferencesUtils.getString(baseContext, "end_date"));
        }

    }

    @OnClick({R.id.tv_title_right, R.id.tv_tixian, R.id.tv_zhuaneng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "105"));
                break;
            case R.id.tv_tixian:
                if ("1".equals(PreferencesUtils.getString(baseContext, "start_flag"))) {
                    StartActivity(TiXianActivity.class);
                } else {
                    showtoa("暂未到提现日期");
                }
                break;
            case R.id.tv_zhuaneng:
                StartActivity(ZhuanZengJifenActivity.class);
                break;
        }
    }
}
