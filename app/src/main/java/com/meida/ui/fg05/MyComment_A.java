package com.meida.ui.fg05;

import android.content.Context;
import android.content.Intent;
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
import com.meida.ui.fg05.child.FG_pingjia;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 评价
 * 这个页面  很奇怪 就怕后期要改
 *
 * @author Administrator-LFC
 *         created at 2018/8/18 11:12
 */
public class MyComment_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tablayout_mc)
    TabLayout tablayoutMc;
    @Bind(R.id.vp_mc)
    ViewPager vpMc;

    private String[] titles = new String[]{"待评价", "已评价"};
    private List<Fragment> mList_Fragments = new ArrayList<>();
    private int CommentType = 0; // 1  待评价 2  已评价

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            CommentType = intent.getExtras().getInt("CommentType");
        }
        initView();
    }

    private void initView() {
        changeTitle("待评价");
        for (int i = 0; i < titles.length; i++) {
            tablayoutMc.addTab(tablayoutMc.newTab().setText(titles[i]));
        }
        FG_pingjia fg_01 = FG_pingjia.newInstance(1, "");
        FG_pingjia fg_02 = FG_pingjia.newInstance(2, "");
        mList_Fragments.add(fg_01);
        mList_Fragments.add(fg_02);
        vpMc.setAdapter(new CustomAdapter(getSupportFragmentManager(), baseContext));
        vpMc.setOffscreenPageLimit(2);
        tablayoutMc.setupWithViewPager(vpMc);
        CommonUtil.reflex(tablayoutMc, 15);
        switch (CommentType) {
            case 1:
                tablayoutMc.getTabAt(0).select();
                break;
            case 2:
                tablayoutMc.getTabAt(1).select();
                break;
            default:
                break;
        }
        vpMc.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        changeTitle("待评价");
                        break;
                    case 1:
                        changeTitle("已评价");

                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

