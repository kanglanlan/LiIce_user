package com.meida.ui.fg05;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.ui.fg05.child.FG_ServiceRecord;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 维修记录
 *
 * @author Administrator-LFC
 *         created at 2018/8/15 17:05
 */
public class ServiceRecord_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tablayout_sr)
    TabLayout tablayoutSr;
    @Bind(R.id.vp_sr)
    ViewPager vpSr;

    private String[] titles = new String[]{"全部", "待维修", "已完成"};
    private List<Fragment> mList_Fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_record);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        changeTitle("维修记录");
        for (int i = 0; i < titles.length; i++) {
            tablayoutSr.addTab(tablayoutSr.newTab().setText(titles[i]));
        }

        FG_ServiceRecord fg01 =new FG_ServiceRecord("0");
        FG_ServiceRecord fg02 = new FG_ServiceRecord("2");
        FG_ServiceRecord fg03 = new FG_ServiceRecord("3");
        mList_Fragments.add(fg01);
        mList_Fragments.add(fg02);
        mList_Fragments.add(fg03);
        vpSr.setAdapter(new CustomAdapter(getSupportFragmentManager(), baseContext));
        tablayoutSr.setupWithViewPager(vpSr);
        CommonUtil.reflex(tablayoutSr, 20);

    }

    private class CustomAdapter extends FragmentStatePagerAdapter {
        private FragmentTransaction mCurTransaction = null;
        FragmentManager fm;
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
            this.fm=supportFragmentManager;
        }
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
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
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            if (mCurTransaction == null) {
                mCurTransaction = fm.beginTransaction();
            }
            mCurTransaction.remove((Fragment)object);
            mCurTransaction.commitNowAllowingStateLoss();
        }
    }



}
