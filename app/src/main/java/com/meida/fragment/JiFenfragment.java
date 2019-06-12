package com.meida.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.meida.liice.App;
import com.meida.liice.QueRenOrderjifenActivity;
import com.meida.liice.R;
import com.meida.liice.ShouYeActivity;
import com.meida.liice.WoYaoTuiJianActivity;
import com.meida.model.Coommt;
import com.meida.model.GoodsInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.photoview.demo.ImagePagerActivity;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class JiFenfragment extends BaseFragment  implements BaseSliderView.OnSliderClickListener{
    @Bind(R.id.slider_shouye_lunbo)
    SliderLayout sliderShouyeLunbo;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_peisong)
    TextView tvPeisong;
    @Bind(R.id.tablayout_mo)
    TabLayout tablayout;
    @Bind(R.id.web_info)
    WebView webInfo;
    String id;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_duihuanjifen)
    TextView tvDuihuanjifen;
    @Bind(R.id.tv_butiejifen)
    TextView tvButiejifen;
    @Bind(R.id.tv_yiduihuan)
    TextView tvYiduihuan;
    @Bind(R.id.tv_kefu)
    TextView tvKefu;
    @Bind(R.id.tv_shoucang)
    TextView tvShoucang;
    @Bind(R.id.tv_duihuan)
    TextView tvDuihuan;
    @Bind(R.id.tv_tuiguang)
    TextView tvTuiguang;
    private Drawable top;

    public JiFenfragment(String id) {
        this.id = id;
    }

    //调用这个方法切换时不会释放掉Fragment
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jifenfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getdata();
        getinfo(1);
    }
    String info = "";

    private void getinfo(final int i) {
        mRequest = NoHttp.createStringRequest(HttpIp.document, Const.POST);
        mRequest.add("type", "106");
        getRequest(new CustomHttpListenermoney<Coommt>(getActivity(), true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    info = data.getData().getDetail();
                    if (i == 2) {
                        webInfo.loadDataWithBaseURL(null, data.getData().getDetail(), "text/html", "utf-8", null);
                    }
                }
            }
        }, false);
    }
    private void init() {
        CommonUtil.setwidhe(sliderShouyeLunbo, App.wid, App.wid);

        TabLayout.Tab tab;
        tablayout.removeAllTabs();
        tablayout.setTabMode(TabLayout.GRAVITY_CENTER);
        tab = tablayout.newTab();
        tab.setText("商品详情");
        tablayout.addTab(tab);
        tab = tablayout.newTab();
        tab.setText("兑换规则");
        tablayout.addTab(tab);
        CommonUtil.setIndicator(tablayout, 10, 10);
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    webInfo.loadDataWithBaseURL(null, goodsInfo.getData().getDetail(), "text/html", "utf-8", null);
                } else {
                    if (TextUtils.isEmpty(info)) {
                        getinfo(2);
                    } else {
                        webInfo.loadDataWithBaseURL(null, info, "text/html", "utf-8", null);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    GoodsInfo goodsInfo;

    public void getdata() {
        mRequest = NoHttp.createStringRequest(HttpIp.goodsDetail, Const.POST);
        mRequest.add("type", "2");    //1商品2兑换商品
        mRequest.add("goodsid", id);
        getRequest(new CustomHttpListener<GoodsInfo>(getActivity(), true, GoodsInfo.class) {
            @Override
            public void doWork(GoodsInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    goodsInfo = data;
                    webInfo.loadDataWithBaseURL(null, goodsInfo.getData().getDetail(), "text/html", "utf-8", null);
                    showlunbo();
                    showsc(data.getData().getStatus());
                    tvYiduihuan.setText(goodsInfo.getData().getUser_sales_num() + "人已兑换");
                    tvName.setText(goodsInfo.getData().getTitle());
                    tvDuihuanjifen.setText(goodsInfo.getData().getSales_score_exchange());
                    tvButiejifen.setText(goodsInfo.getData().getSales_score());
                }
            }
        }, true);
    }

    private void showlunbo() {
        //轮播图
        try {
            sliderShouyeLunbo.removeAllSliders();
            for (int i = 0; i < goodsInfo.getData().getSmeta().size(); i++) {
                DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
                if (TextUtils.isEmpty(goodsInfo.getData().getSmeta().get(i).getUrl())) {
                    textSliderView.image("http:www.baidu.com")
                            .setOnSliderClickListener(this)
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop);
                } else {
                    textSliderView.image(goodsInfo.getData().getSmeta().get(i).getUrl())
                            .setOnSliderClickListener(this)
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop);
                }
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("index", i + "");
                sliderShouyeLunbo.addSlider(textSliderView);
            }
            sliderShouyeLunbo.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            // 设置自定义指示器(位置自定义)
//            sliderShouyeLunbo.setCustomIndicator(indicator);
            sliderShouyeLunbo.setCustomAnimation(new DescriptionAnimation());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_kefu, R.id.tv_shoucang, R.id.tv_duihuan, R.id.tv_tuiguang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_kefu:
                Intent inten = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PreferencesUtils.getString(getActivity(), "service_tel")));
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
                break;
            case R.id.tv_shoucang:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                Nonce.setsc(id, getActivity(), new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        showsc(string);
                    }
                });
                break;
            case R.id.tv_duihuan:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                startActivity(new Intent(getActivity(), QueRenOrderjifenActivity.class)
                        .putExtra("id", goodsInfo.getData().getId())
                        .putExtra("name", goodsInfo.getData().getTitle())
                        .putExtra("img", goodsInfo.getData().getLogo())
                        .putExtra("duihuan", tvDuihuanjifen.getText().toString())
                        .putExtra("butie", tvButiejifen.getText().toString())
                );
                break;
            case R.id.tv_tuiguang:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                StartActivity(WoYaoTuiJianActivity.class);
                break;
        }
    }


    //收藏状态 0未收藏 1 已收藏
    private void showsc(String status) {
        if ("1".equals(status)) {
            top = getResources().getDrawable(R.mipmap.ico_lb022);// 获取res下的图片drawable
            tvShoucang.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        }
        if ("0".equals(status)) {
            top = getResources().getDrawable(R.mipmap.ico_lb021);// 获取res下的图片drawable
            tvShoucang.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int position = Integer.parseInt(slider.getBundle().get("index") + "");
        String[] aStrings = new String[goodsInfo.getData().getSmeta().size()];
        for (int i = 0; i < goodsInfo.getData().getSmeta().size(); i++) {
            aStrings[i] = goodsInfo.getData().getSmeta().get(i).getUrl();
        }
        Intent intent = new Intent(getActivity(),
                ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                aStrings);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,
                position);
        startActivity(intent);
    }
}
