package com.meida.liice;

import android.os.Bundle;
import android.widget.TextView;

import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZhuanZengOkActivity extends BaseActivity {

    @Bind(R.id.tv_jifen)
    TextView tvJifen;
    @Bind(R.id.tv_leiji)
    TextView tvLeiji;
    @Bind(R.id.tv_dangqian)
    TextView tvDangqian;
    @Bind(R.id.tv_yizhuan)
    TextView tvYizhuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_zeng_ok);
        ButterKnife.bind(this);
        changeTitle("转赠成功");
        tvJifen.setText(getIntent().getStringExtra("jifen") + "积分");
        mRequest = NoHttp.createStringRequest(HttpIp.info, Const.POST);
        getRequest(new CustomHttpListenermoney<Login>(baseContext, true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                if ("1".equals(data.getCode())) {
                    Nonce.putinfo(baseContext, data);
                    tvLeiji.setText("累计积分："+PreferencesUtils.getString(baseContext, "total_score"));//补贴积分
                    tvYizhuan.setText("已转积分："+PreferencesUtils.getString(baseContext, "total_score_give"));//兑换积分
                    tvDangqian.setText("当前积分："+PreferencesUtils.getString(baseContext, "score"));//补贴
                }
            }
        }, true);
    }
}
