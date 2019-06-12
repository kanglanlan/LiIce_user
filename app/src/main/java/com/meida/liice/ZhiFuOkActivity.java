package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meida.ui.fg05.MyOrders_A;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.GouWuCheActivity.gouwu;
import static com.meida.liice.QueRenOrderActivity.querenorder;

public class ZhiFuOkActivity extends BaseActivity {

    @Bind(R.id.tv_qian)
    TextView tvQian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu_ok);
        ButterKnife.bind(this);
        changeTitle("支付成功");
        tvQian.setText(getIntent().getStringExtra("price") + "元");
    }

    @OnClick({R.id.bt_kanorder, R.id.bt_shouye})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_kanorder:
                startActivity(new Intent(baseContext,MyOrders_A.class).putExtra("OrderType", 0));
                if (gouwu != null) {
                    gouwu.finish();
                }
                if (querenorder != null) {
                    querenorder.finish();
                }
                finish();
                break;
            case R.id.bt_shouye:
                finishAffinity();
                break;
        }
    }
}
