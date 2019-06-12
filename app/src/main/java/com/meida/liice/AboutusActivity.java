package com.meida.liice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.utils.CommonUtil;
import com.meida.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutusActivity extends BaseActivity {

    @Bind(R.id.tv_banben)
    TextView tvBanben;
    @Bind(R.id.tv_gongzhognhao)
    TextView tvGongzhognhao;
    @Bind(R.id.tv_dianhua)
    TextView tvDianhua;
    @Bind(R.id.ll_dianhua)
    LinearLayout ll_dianhua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        changeTitle("关于我们");
        changeTitle("githib");
        try {
            tvBanben.setText("立冰补贴商城" + CommonUtil.getVersion(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvDianhua.setText(PreferencesUtils.getString(baseContext,"service_tel"));
        tvGongzhognhao.setText(PreferencesUtils.getString(baseContext,"service_weixin"));
        ll_dianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PreferencesUtils.getString(baseContext, "service_tel")));
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
            }
        });
    }

    @OnClick(R.id.tv_fuwutiaokuam)
    public void onClick() {
        startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "112"));

    }
}
