package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenZhengJieGuoActivity extends BaseActivity {

    @Bind(R.id.img_pic)
    ImageView imgPic;
    @Bind(R.id.tv_jieguo)
    TextView tvJieguo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_code)
    TextView tvCode;
    @Bind(R.id.ll_ok)
    LinearLayout llOk;
    @Bind(R.id.tv_yuanyin)
    TextView tvYuanyin;
    @Bind(R.id.ll_shibai)
    LinearLayout llShibai;
    @Bind(R.id.bt_rz)
    Button btRz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_zheng_jie_guo);
        ButterKnife.bind(this);
        changeTitle("实名认证");

        if ("2".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
            tvJieguo.setText("已通过");
            llOk.setVisibility(View.VISIBLE);
            tvCode.setText(PreferencesUtils.getString(baseContext, "code"));
            tvName.setText(PreferencesUtils.getString(baseContext, "user_real_name"));
            Nonce.getcard_info(baseContext, new Nonce.StringsCallback() {
                @Override
                public void doWork(String string1, String string2,String s) {
                    tvCode.setText(string2);
                    tvName.setText(string1);
                }
            });
        } else {
            tvJieguo.setText("未通过");
            tvJieguo.setTextColor(getResources().getColor(R.color.main));
            imgPic.setImageResource(R.mipmap.ico_lb108);
            llShibai.setVisibility(View.VISIBLE);
            Nonce.getcard_info(baseContext, new Nonce.StringsCallback() {
                @Override
                public void doWork(String string1, String string2,String s) {
                    tvYuanyin.setText("失败原因：" +s);
                }
            });

            btRz.setVisibility(View.VISIBLE);
        }
        // 实名认证 0：未认证1：认证中2：认证成功 3：认证失败
    }
    @OnClick(R.id.bt_rz)
    public void onClick() {
        StartActivity(ShiMingRenZhengActivity.class);
        finish();
    }
}
