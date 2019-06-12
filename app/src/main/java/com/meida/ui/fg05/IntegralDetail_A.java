package com.meida.ui.fg05;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.ui.fg05.child.FG_Income;
import com.meida.ui.fg05.child.FG_Pay;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分明细
 *
 * @author Administrator-LFC
 *         created at 2018/8/14 10:24
 */

public class IntegralDetail_A extends BaseActivity {

    @Bind(R.id.tablayout_id)
    TabLayout tablayoutId;
    @Bind(R.id.vp_id)
    ViewPager vpId;
    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    private String[] titles = new String[]{"收入", "支出"};
    private List<Fragment> mList_Fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail);
        ButterKnife.bind(this);
        initView();
        getData(true);
    }

    private void initView() {
        changeTitle("积分明细");
        for (int i = 0; i < titles.length; i++) {
            tablayoutId.addTab(tablayoutId.newTab().setText(titles[i]));
        }

        FG_Income fg_income = new FG_Income();
        FG_Pay fg_pay = new FG_Pay();
        mList_Fragments.add(fg_income);
        mList_Fragments.add(fg_pay);
        vpId.setAdapter(new CustomAdapter(getSupportFragmentManager(), baseContext));
        vpId.setOffscreenPageLimit(2);
        tablayoutId.setupWithViewPager(vpId);
        CommonUtil.reflex(tablayoutId, 30);

    }

    private void getData(boolean isload) {

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
