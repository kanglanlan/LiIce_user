package com.meida.ui.fg05;

import android.os.Bundle;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralDetailActivity extends BaseActivity {

    @Bind(R.id.tv_liushuihao)
    TextView tvLiushuihao;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_zhichu)
    TextView tvZhichu;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_yue)
    TextView tvYue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail2);
        ButterKnife.bind(this);
        changeTitle("详情");
        tvLiushuihao.setText(getIntent().getStringExtra("liushui"));
        tvLeixing.setText(getIntent().getStringExtra("type"));
        tvZhichu.setText(getIntent().getStringExtra("zhichu")+"积分");
        tvTime.setText(getIntent().getStringExtra("time"));
        tvYue.setText(getIntent().getStringExtra("yue")+"积分");
    }
}
