package com.meida.ui.fg05;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.MyView.CircleImageView;
import com.meida.liice.BaseActivity;
import com.meida.liice.BuTieJiFenActivity;
import com.meida.liice.IntegralTiXianActivity;
import com.meida.liice.R;
import com.meida.model.Login;
import com.meida.ui.fg05.child.FG_GetIntegral;
import com.meida.ui.fg05.child.FG_UseIntegral;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的积分
 *
 * @author Administrator-LFC
 *         created at 2018/8/13 14:03
 */
public class MyIntegral_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.img_head_mi)
    CircleImageView imgHeadMi;
    @Bind(R.id.tv_name_mi)
    TextView tvNameMi;
    @Bind(R.id.tv_id_mi)
    TextView tvIdMi;
    @Bind(R.id.tv_integral01_mi)
    TextView tvIntegral01Mi;
    @Bind(R.id.tv_integral02_mi)
    TextView tvIntegral02Mi;
    @Bind(R.id.tv_integral03_mi)
    TextView tvIntegral03Mi;
    @Bind(R.id.tablayout_mi)
    TabLayout tablayoutMi;
    @Bind(R.id.vp_mi)
    ViewPager vpMi;
    @Bind(R.id.ll_3)
    LinearLayout ll_3;
    @Bind(R.id.ll_butiejifen)
    LinearLayout ll_butiejifen;

    private String[] titles = new String[]{"花积分", "赚积分"};
    private List<Fragment> mList_Fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integral);
        ButterKnife.bind(this);
        Nonce.getsys(baseContext);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Nonce.getsys(baseContext);
        Nonce.getinfo(baseContext, new Nonce.LoginCallback() {
            @Override
            public void doWork(Login string) {
                tvIntegral01Mi.setText(PreferencesUtils.getString(baseContext, "total_payment_score"));//补贴积分
                tvIntegral02Mi.setText(PreferencesUtils.getString(baseContext, "score_exchange"));//兑换积分
                tvIntegral03Mi.setText(PreferencesUtils.getString(baseContext, "score"));//补贴
            }
        });
    }

    private void initView() {
        changeTitle("我的积分");
        tvTitleRight.setText("积分明细");
        CommonUtil.setcimg(baseContext, PreferencesUtils.getString(baseContext, "logo"),
                R.mipmap.ico_lb084, imgHeadMi);
            tvNameMi.setText(PreferencesUtils.getString(baseContext, "nickname"));

        tvIdMi.setText("ID:" + PreferencesUtils.getString(baseContext, "uid"));
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(baseContext, IntegralDetail_A.class));
            }
        });
        tvIntegral01Mi.setText(PreferencesUtils.getString(baseContext, "total_payment_score"));//补贴积分
        tvIntegral02Mi.setText(PreferencesUtils.getString(baseContext, "score_exchange"));//兑换积分
        tvIntegral03Mi.setText(PreferencesUtils.getString(baseContext, "score"));//补贴
        initTab();
        ll_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity(IntegralTiXianActivity.class);
            }
        });
        ll_butiejifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity(BuTieJiFenActivity.class);
            }
        });
    }

    private void initTab() {

        for (int i = 0; i < titles.length; i++) {
            tablayoutMi.addTab(tablayoutMi.newTab().setText(titles[i]));
        }

        FG_GetIntegral fg_getIntegral = new FG_GetIntegral();
        FG_UseIntegral fg_useIntegral = new FG_UseIntegral();
        mList_Fragments.add(fg_useIntegral);
        mList_Fragments.add(fg_getIntegral);
        vpMi.setAdapter(new CustomAdapter(getSupportFragmentManager(), baseContext));
        vpMi.setOffscreenPageLimit(2);
        tablayoutMi.setupWithViewPager(vpMi);
        CommonUtil.reflex(tablayoutMi, 30);
    }


    private class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mList_Fragments.get(position);
        }

        @Override
        public int getCount() {
            return mList_Fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }


}
