package com.meida.liice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.meida.utils.PreferencesUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiaMengokActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai2);
        ButterKnife.bind(this);
        changeTitle("服务商加盟");
    }

    @OnClick(R.id.tv_xiazai)
    public void onClick() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PreferencesUtils.getString(baseContext,"url_down_merchant"))));
        } catch (Exception e) {
            showtoa("链接不正确");
            e.printStackTrace();
        }

    }
}
