package com.meida.liice;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralRewardokActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_rewardok);
        ButterKnife.bind(this);
        changeTitle("积分打赏");
    }

    @OnClick(R.id.tv_shouye)
    public void onClick() {
        finishAffinity();
    }
}
