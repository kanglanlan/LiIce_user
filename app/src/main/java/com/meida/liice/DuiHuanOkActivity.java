package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meida.ui.fg05.ExchangedGoods_A;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuiHuanOkActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_paytype)
    TextView tvPaytype;
    @Bind(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dui_huan_ok);
        ButterKnife.bind(this);
        changeTitle("兑换成功");
        tvBianhao.setText(getIntent().getStringExtra("oid"));
        tvTime.setText(getIntent().getStringExtra("time"));
        tvName.setText(getIntent().getStringExtra("name"));
        tvPaytype.setText(getIntent().getStringExtra("type"));
    }

    @OnClick({R.id.bt_kanorder, R.id.bt_shouye})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_kanorder:
                StartActivity(ExchangedGoods_A.class);
                finish();
                break;
            case R.id.bt_shouye:
                finishAffinity();
                break;
        }
    }
}
