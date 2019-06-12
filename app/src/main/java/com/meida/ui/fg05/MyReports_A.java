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
import com.meida.ui.fg05.child.FG_Reports;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 报备的项目
 *
 * @author Administrator-LFC
 *         created at 2018/8/14 17:00
 */
public class MyReports_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tablayout_reports)
    TabLayout tablayoutReports;
    @Bind(R.id.vp_reports)
    ViewPager vpReports;

    private String[] titles = new String[]{"全部", "成功", "失败"};
    private List<Fragment> mList_Fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        changeTitle("报备的项目");
        for (int i = 0; i < titles.length; i++) {
            tablayoutReports.addTab(tablayoutReports.newTab().setText(titles[i]));
        }
        FG_Reports fg_01 = FG_Reports.newInstance("0", "");
        FG_Reports fg_02 = FG_Reports.newInstance("2", "");
        FG_Reports fg_03 = FG_Reports.newInstance("1", "");
        mList_Fragments.add(fg_01);
        mList_Fragments.add(fg_02);
        mList_Fragments.add(fg_03);
        vpReports.setAdapter(new CustomAdapter(getSupportFragmentManager(), baseContext));
        vpReports.setOffscreenPageLimit(3);
        tablayoutReports.setupWithViewPager(vpReports);
        CommonUtil.reflex(tablayoutReports, 30);
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

