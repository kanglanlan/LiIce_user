package com.meida.liice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.meida.adapter.MyFragmentPagerAdapter;
import com.meida.fragment.JiFenfragment;
import com.meida.fragment.pingjiafragment;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiFenDuiHuanActivity extends BaseActivity {
    @Bind(R.id.tablayout_mo)
    TabLayout tablayout;
    @Bind(R.id.vp_mo)
    ViewPager viewpager;

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private MyFragmentPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_tie_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        TabLayout.Tab tab;
        tablayout.removeAllTabs();
        tablayout.setTabMode(TabLayout.GRAVITY_CENTER);
        tab = tablayout.newTab();
        tab.setText("商品");
        tablayout.addTab(tab);
        tab = tablayout.newTab();
        tab.setText("评价");
        tablayout.addTab(tab);
        JiFenfragment sqfragment = new JiFenfragment(getIntent().getStringExtra("id"));
        fragments.add(sqfragment);
        pingjiafragment sqfragment2 = new pingjiafragment(getIntent().getStringExtra("id"),"2");
        fragments.add(sqfragment2);
        tablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        CommonUtil.setIndicator(tablayout,10,10);
    }

    @OnClick(R.id.img_title_back)
    public void onClick() {
        finish();
    }
}
