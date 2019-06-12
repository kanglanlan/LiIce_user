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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.meida.liice.App;
import com.meida.liice.GouWuCheActivity;
import com.meida.liice.QueRenOrderActivity;
import com.meida.liice.R;
import com.meida.liice.ShouYeActivity;
import com.meida.liice.WoYaoTuiJianActivity;
import com.meida.liice.ZuLinActivity;
import com.meida.model.Cars;
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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.R.id.tv_jiage;
import static com.meida.share.Datas.CITY;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class shangpinfragment extends BaseFragment implements BaseSliderView.OnSliderClickListener {

    @Bind(R.id.slider_shouye_lunbo)
    SliderLayout sliderShouyeLunbo;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_cartnum)
    TextView tv_cartnum;
    @Bind(tv_jiage)
    TextView tvJiage;
    @Bind(R.id.tv_fukuannum)
    TextView tvFukuannum;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sc)
    TextView tvSc;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_goueuche)
    TextView tv_goueuche;
    @Bind(R.id.ll_biaoqian)
    LinearLayout llBiaoqian;
    @Bind(R.id.tv_guige)
    TextView tvGuige;
    @Bind(R.id.tv_butie)
    TextView tvButie;
    @Bind(R.id.tv_peisong)
    TextView tvPeisong;
    @Bind(R.id.tablayout_mo)
    TabLayout tablayout;
    @Bind(R.id.web_info)
    WebView webInfo;
    String id;
    @Bind(R.id.tv_tc1)
    TextView tvTc1;
    @Bind(R.id.tv_tc2)
    TextView tvTc2;
    @Bind(R.id.tv_tc3)
    TextView tvTc3;
    private Drawable top;


    public shangpinfragment(String id) {
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
        View view = inflater.inflate(R.layout.shangjiafragment, container, false);
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
        mRequest.add("type", "107");
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
        tab.setText("常见问题");
        tablayout.addTab(tab);
        CommonUtil.setIndicator(tablayout, 10, 10);
        CommonUtil.websetting(getActivity(), webInfo);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PreferencesUtils.getInt(getActivity(), "login") == 1) {
            Nonce.getcartnum(getActivity(), new Nonce.StringCallback() {
                @Override
                public void doWork(String string) {
                    if (!"0".equals(string)) {
                        tv_cartnum.setVisibility(View.VISIBLE);
                        tv_cartnum.setText(string);
                    } else {
                        tv_cartnum.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    ArrayList<Cars.DataBean.ListBean> datas = new ArrayList<>();

    @OnClick({R.id.tv_sc, R.id.tv_butie, R.id.tv_tuiguang, R.id.tv_kefu, R.id.tv_goueuche, R.id.tv_duihuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_duihuan:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                final View view1 = View.inflate(getActivity(), R.layout.popu_shop, null);
                ImageView imageView = (ImageView) view1.findViewById(R.id.img_cover);
                TextView name = (TextView) view1.findViewById(R.id.tv_goods_name);
                TextView price = (TextView) view1.findViewById(R.id.tv_goods_price);
                TextView jia = (TextView) view1.findViewById(R.id.tv_jia);
                TextView jian = (TextView) view1.findViewById(R.id.tv_jian);
                final TextView nums = (TextView) view1.findViewById(R.id.tv_nums);
                TextView tv_addcart = (TextView) view1.findViewById(R.id.tv_addcart);
                TextView tv_shop = (TextView) view1.findViewById(R.id.tv_shop);
                CommonUtil.setimg(getActivity(), goodsInfo.getData().getLogo(), R.drawable.moren, imageView);
                name.setText(goodsInfo.getData().getTitle());
                price.setText(getActivity().getResources().getString(R.string.qian)
                        + goodsInfo.getData().getUser_price());
                final BottomBaseDialog sharedialog = new BottomBaseDialog(getActivity()) {
                    @Override
                    public View onCreateView() {
                        return view1;
                    }

                    @Override
                    public void setUiBeforShow() {
                    }
                };
                sharedialog.show();
                jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nums.setText((Integer.parseInt(nums.getText().toString()) + 1) + "");
                    }
                });
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(nums.getText().toString()) > 1) {
                            nums.setText((Integer.parseInt(nums.getText().toString()) - 1) + "");
                        }
                    }
                });
                tv_shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedialog.dismiss();
                        datas.clear();
                        Cars.DataBean.ListBean listBean = new Cars.DataBean.ListBean();
                        listBean.setProduct_num(nums.getText().toString());
                        listBean.setTitle(goodsInfo.getData().getTitle());
                        listBean.setPrice(goodsInfo.getData().getUser_price());
                        listBean.setCheck(1);
                        listBean.setLogo(goodsInfo.getData().getLogo());
                        listBean.setProduct_id(goodsInfo.getData().getId());
                        datas.add(listBean);
                        Intent intent = new Intent(getActivity(), QueRenOrderActivity.class);
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("list", datas);
                        intent.putExtras(bundleObject);
                        intent.putExtra("qian", (Integer.parseInt(nums.getText().toString())
                                * Double.parseDouble(goodsInfo.getData().getUser_price())) + "");
                        intent.putExtra("allnums", nums.getText().toString());
                        startActivity(intent);
                    }
                });
                tv_addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Nonce.changecart(id, nums.getText().toString(), "", getActivity(), new Nonce.StringCallback() {
                            @Override
                            public void doWork(String string) {
                                if (PreferencesUtils.getInt(getActivity(), "login") == 1) {
                                    Nonce.getcartnum(getActivity(), new Nonce.StringCallback() {
                                        @Override
                                        public void doWork(String string) {
                                            sharedialog.dismiss();
                                            if (!"0".equals(string)) {
                                                tv_cartnum.setVisibility(View.VISIBLE);
                                                tv_cartnum.setText(string);
                                            } else {
                                                tv_cartnum.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

//
                break;
            case R.id.tv_sc:
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
            case R.id.tv_butie:
                startActivity(new Intent(getActivity(), ZuLinActivity.class).putExtra("tag", "108"));
                break;
            case R.id.tv_tuiguang:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                startActivity(new Intent(getActivity(), WoYaoTuiJianActivity.class));
                break;
            case R.id.tv_kefu:
                Intent inten = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PreferencesUtils.getString(getActivity(), "service_tel")));
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
                break;
            case R.id.tv_goueuche:
                if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                StartActivity(GouWuCheActivity.class);
                break;
        }
    }


    GoodsInfo goodsInfo;

    public void getdata() {
        mRequest = NoHttp.createStringRequest(HttpIp.goodsDetail, Const.POST);
        mRequest.add("type", "1");    //1商品2兑换商品
        mRequest.add("goodsid", id);
        getRequest(new CustomHttpListener<GoodsInfo>(getActivity(), true, GoodsInfo.class) {
            @Override
            public void doWork(GoodsInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    goodsInfo = data;
                    webInfo.loadDataWithBaseURL(null, goodsInfo.getData().getDetail(), "text/html", "utf-8", null);
                    showlunbo();
                    showsc(data.getData().getStatus());
                    tvJiage.setText(goodsInfo.getData().getUser_price());
                    tvFukuannum.setText(goodsInfo.getData().getUser_sales_num() + "人购买");
                    tvName.setText(goodsInfo.getData().getTitle());
                    tvType.setText(goodsInfo.getData().getContent());
                    if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "product_block1"))) {
                        tvTc1.setText(PreferencesUtils.getString(getActivity(), "product_block1"));
                    }
                    if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "product_block2"))) {
                        tvTc2.setText(PreferencesUtils.getString(getActivity(), "product_block2"));
                    }
                    if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "product_block3"))) {
                        tvTc3.setText(PreferencesUtils.getString(getActivity(), "product_block3"));
                    } else {
                        tvTc3.setText(CITY);
                    }
                    if (goodsInfo.getData().getLabel_title().size() > 0) {
                        for (int i = 0; i < goodsInfo.getData().getLabel_title().size(); i++) {
                            View view = View.inflate(getActivity(), R.layout.item_biaoqian, null);
                            TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                            textView.setText(goodsInfo.getData().getLabel_title().get(i));
                            llBiaoqian.addView(view);
                        }
                    }
                    tvGuige.setText(goodsInfo.getData().getSpec());

                    if ("0".equals(goodsInfo.getData().getUser_score()) || "0.00".equals(goodsInfo.getData().getUser_score())) {
                        tvButie.setText("享" + goodsInfo.getData().getUser_score_exchange() + "商品兑换积分");
                    } else if ("0".equals(goodsInfo.getData().getUser_score_exchange()) || "0.00".equals(goodsInfo.getData().getUser_score_exchange())) {
                        tvButie.setText("享" + goodsInfo.getData().getUser_score() + "元电费补贴");
                    } else {
                        tvButie.setText("享" + goodsInfo.getData().getUser_score() + "元电费补贴 + "
                                + goodsInfo.getData().getUser_score_exchange() + "商品兑换积分");
                    }
//                    tvButie.setText("享" + goodsInfo.getData().getUser_score() + "元电费补贴 + " +
//                            goodsInfo.getData().getUser_score_exchange() + "商品兑换积分");

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

    //收藏状态 0未收藏 1 已收藏
    private void showsc(String status) {
        if ("1".equals(status)) {
            top = getResources().getDrawable(R.mipmap.ico_lb022);// 获取res下的图片drawable
            tvSc.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        }
        if ("0".equals(status)) {
            top = getResources().getDrawable(R.mipmap.ico_lb021);// 获取res下的图片drawable
            tvSc.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
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
