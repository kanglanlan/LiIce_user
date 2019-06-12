package com.meida.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.meida.MyView.CustomGridView;
import com.meida.liice.App;
import com.meida.liice.AppCaoZuoActivity;
import com.meida.liice.BuTieInfoActivity;
import com.meida.liice.BuTieKongTiaoActivity;
import com.meida.liice.JiFenModeActivity;
import com.meida.liice.JiaMengwebActivity;
import com.meida.liice.MainActivity;
import com.meida.liice.MessageActivity;
import com.meida.liice.R;
import com.meida.liice.ShouYeActivity;
import com.meida.liice.ZuLinActivity;
import com.meida.model.Coommt;
import com.meida.model.Goods;
import com.meida.model.LunBoTu;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.AdDialog;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.sunfusheng.marqueeview.MarqueeView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.CacheMode;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimAdapterEx;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.meida.share.Datas.CITY;


/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class fragment1 extends BaseFragment implements BaseSliderView.OnSliderClickListener ,AMapLocationListener{
    CustomGridView gridView;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.img_xiaoxi)
    ImageView img_xiaoxi;
    @Bind(R.id.img_kefu)
    ImageView img_kefu;
    @Bind(R.id.tv_ti)
    TextView tv_ti;
    @Bind(R.id.recycle_lisst)
    RecyclerView recycleLisst;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private int pager = 1;
    ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<>();
    MainActivity mainActivity;
    MarqueeView marqueeView;
    //    ImageView img_xiaoxi;
    private SliderLayout sliderShouyeLunbo;
    private AlertDialog dialog;
    @Bind(R.id.img_bgr)
    View img_bg;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器


    public static fragment1 instantiation() {
        fragment1 fragment = new fragment1();
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        initview();
        initmap();
        getdata();
        getmokuan();
        getadd();
        getlist();
        getlunbo(1);






        img_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PreferencesUtils.getString(getActivity(), "service_tel")));
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
            }
        });
        img_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity(MessageActivity.class);
            }
        });
        Nonce.Updateversion(false, getActivity(), new Nonce.CommCallback() {
            @Override
            public void doWork(Coommt string) {
//                        is_force  1强制更新2不强制
//                        type  1用户端2商户端
                banBenM = string;
                if (Integer.parseInt(CommonUtil.getVersionnum(getActivity()))
                        < string.getData().getVersion_code()) {
                    show(string);
                }
            }
        });
    }

    private void initmap() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
//设置定位回调监听
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(200000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    /**
     * //广告类型1轮播图2弹窗广告
     *
     * @param i
     */
    LunBoTu lunBoTu;
    private int height = 200;// 滑动开始变色的高,真实项目中此高度是由广告轮播或其他首页view高度决定
    private int overallXScroll = 0;

    private void getlunbo(final int i) {
        mRequest = NoHttp.createStringRequest(HttpIp.bannerlist, Const.POST);
        mRequest.add("type", i);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        mRequest.setCacheKey("logo");
        getRequest(new CustomHttpListener<LunBoTu>(getActivity(), true, LunBoTu.class) {
            @Override
            public void doWork(LunBoTu data, String code) {
                if ("1".equals(data.getCode())) {
                    if (i == 1) {
                        lunBoTu = data;
                        setlunbo();
                    } else {
                        if (!isshow) {
                            isshow = true;
                            setad(data.getData().getList().get(0));//广告弹窗
                        }
                    }
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                /** 倒计时3秒，一次1秒 */
                new CountDownTimer(1 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //倒计时的过程中回调该函数
                    }

                    @Override
                    public void onFinish() {
                        if (!isget) {
                            isget = true;
                            getlunbo(2);
                        }


                    }
                }.start();
            }
        }, false);
    }

    private boolean isshow, isget;

    private void setad(LunBoTu.DataBean.ListBean listBean) {
        AdDialog adDialog = new AdDialog(getActivity(), listBean);
        adDialog.show();
    }

    private void setlunbo() {
        //轮播图
        try {
            sliderShouyeLunbo.removeAllSliders();
            for (int i = 0; i < lunBoTu.getData().getList().size(); i++) {
                DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
                if (TextUtils.isEmpty(lunBoTu.getData().getList().get(i).getLogo())) {
                    textSliderView.image("http:www.baidu.com")
                            .setOnSliderClickListener(this)
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop);
                } else {
                    textSliderView.image(lunBoTu.getData().getList().get(i).getLogo())
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

    private void initview() {

        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.main));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        recycleLisst.setFocusableInTouchMode(false);
        View view = View.inflate(getActivity(), R.layout.info_shouyetop, null);
        sliderShouyeLunbo = (SliderLayout) view.findViewById(R.id.slider_shouye_lunbo);
        int he = 671 * App.wid / 1142;
        CommonUtil.setwidhe(sliderShouyeLunbo, App.wid, he);
        gridView = (CustomGridView) view.findViewById(R.id.gv_sy);
//        img_xiaoxi = (ImageView) view.findViewById(img_xiaoxi);
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView_fragment1);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                getmokuan();
                getlunbo(1);
                getlist();
            }
        });
        recycleLisst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
                if (overallXScroll <= 0) {   //设置标题的背景颜色
                    img_bg.setBackgroundColor(Color.argb((int) 0, 21, 131, 230));
                    tv_ti.setVisibility(View.INVISIBLE);
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    img_bg.setBackgroundColor(Color.argb((int) alpha, 21, 131, 230));
                    tv_ti.setVisibility(View.INVISIBLE);
                } else {
                    img_bg.setBackgroundColor(Color.argb((int) 255, 21, 131, 230));
                    tv_ti.setVisibility(View.VISIBLE);
                }


                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                if (lastVisibleItem >= total - 3 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getlist();
                    }
                }
            }
        });
        mAdapterex = SlimAdapter.create(SlimAdapterEx.class).addHeader(view)
                .register(R.layout.item_butieshangpin, new SlimInjector<Goods.DataBean.DataBeans>() {
                    @Override
                    public void onInject(final Goods.DataBean.DataBeans s, IViewInjector injector) {
                        ImageView imageView = (ImageView) injector.findViewById(R.id.img_goumai_photo);
                        CommonUtil.setimg(getActivity(), s.getLogo(), R.drawable.moren, imageView);
                        injector.text(R.id.tv_name, s.getTitle());
                        injector.text(R.id.tv_content, s.getContent());
                        injector.text(R.id.tv_content, s.getContent());
                        injector.text(R.id.tv_money, s.getUser_price());
                        injector.text(R.id.tv_goumai, s.getUser_sales_num() + "人购买");
                        if ("0".equals(s.getUser_score()) || "0.00".equals(s.getUser_score())) {
                            injector.text(R.id.tv_butie, "享" + s.getUser_score_exchange() + "商品兑换积分");
                        } else if ("0".equals(s.getUser_score_exchange()) || "0.00".equals(s.getUser_score_exchange())) {
                            injector.text(R.id.tv_butie, "享" + s.getUser_score() + "元电费补贴");
                        } else {
                            injector.text(R.id.tv_butie, "享" + s.getUser_score() + "元电费补贴 + "
                                    + s.getUser_score_exchange() + "商品兑换积分");
                        }
                        LinearLayout ll_bq = (LinearLayout) injector.findViewById(R.id.ll_bq);
                        ll_bq.removeAllViews();
                        if (s.getLabels().size() > 0) {
                            for (int i = 0; i < s.getLabels().size(); i++) {
                                View view = View.inflate(getActivity(), R.layout.item_biaoqian, null);
                                TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                                textView.setText(s.getLabels().get(i));
                                ll_bq.addView(view);
                            }
                        }
                        injector.clicked(R.id.ll_butie, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getActivity(), BuTieInfoActivity.class).putExtra("id", s.getId()));

                            }
                        });
                    }
                }).attachTo(recycleLisst);


    }

    public void getdata() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (Native.getData().getList().get(i).getType()) {
                    case "1":
                        StartActivity(BuTieKongTiaoActivity.class);
                        break;
                    case "2":
                        if (PreferencesUtils.getInt(getActivity(), "login") != 1) {
                            StartActivity(ShouYeActivity.class);
                            return;
                        }
                        StartActivity(JiFenModeActivity.class);
                        break;
                    case "3":
                        getActivity().startActivity(new Intent(getActivity(), ZuLinActivity.class).putExtra("tag", "102"));
                        break;
                    case "4":
                        mainActivity.change(0);
                        break;
                    case "8":
                        mainActivity.change(1);
                        break;
                    case "5":
                        StartActivity(JiaMengwebActivity.class);
                        break;
                    case "6":
                        StartActivity(AppCaoZuoActivity.class);
                        break;
                    case "7":
                        mainActivity.change(2);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int position = Integer.parseInt(slider.getBundle().get("index") + "");
        switch (lunBoTu.getData().getList().get(position).getRedirect_type()) {
            case "2":
                startActivity(new Intent(getActivity(), ZuLinActivity.class)
                        .putExtra("tag", "2")
                        .putExtra("info", lunBoTu.getData().getList().get(position).getRedirect_value())
                );
                break;
            case "3":
                startActivity(new Intent(getActivity(), ZuLinActivity.class)
                        .putExtra("tag", "1")
                        .putExtra("info", lunBoTu.getData().getList().get(position).getDetail())
                );
                break;
            case "4":
                startActivity(new Intent(getActivity(), BuTieInfoActivity.class)
                        .putExtra("id", lunBoTu.getData().getList().get(position).getRedirect_value()));
                break;
        }
    }

    /**
     * 首页模块
     */
    LunBoTu Native;

    public void getmokuan() {
        mRequest = NoHttp.createStringRequest(HttpIp.navlist, Const.POST);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        mRequest.setCacheKey("title");
        getRequest(new CustomHttpListener<LunBoTu>(getActivity(), true, LunBoTu.class) {
            @Override
            public void doWork(LunBoTu data, String code) {
                if ("1".equals(data.getCode())) {
                    Native = data;
                    gridView.setAdapter(new CommonAdapter<LunBoTu.DataBean.ListBean>(getActivity(),
                            R.layout.item_shouye, Native.getData().getList()) {
                        @Override
                        protected void convert(ViewHolder viewHolder, LunBoTu.DataBean.ListBean item, int position) {
                            viewHolder.setText(R.id.tv_syname, item.getTitle());
                            ImageView img = viewHolder.getView(R.id.img_sy);
                            CommonUtil.setimg(getActivity(), item.getLogo(), R.drawable.moren, img);
                        }
                    });
                }
            }
        }, true);
    }

    /**
     * 公告
     */
    public void getadd() {
        mRequest = NoHttp.createStringRequest(HttpIp.noticelist, Const.POST);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        mRequest.setCacheKey("title");
        getRequest(new CustomHttpListener<LunBoTu>(getActivity(), true, LunBoTu.class) {
            @Override
            public void doWork(LunBoTu data, String code) {
                if ("1".equals(data.getCode())) {
                    ArrayList<String> news = new ArrayList<>();
                    for (int i = 0; i < data.getData().getList().size(); i++) {
                        news.add(data.getData().getList().get(i).getNotice());
                    }
                    marqueeView.startWithList(news);
                }
            }
        }, false);
    }

    public void getlist() {
        mRequest = NoHttp.createStringRequest(HttpIp.goodslist, Const.POST);
        mRequest.add("type", "1");    //1商品2兑换商品
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<Goods>(getActivity(), true, Goods.class) {
            @Override
            public void doWork(Goods data, String code) {
                if ("1".equals(data.getCode())) {
                    if (pager == 1) {
                        datas.clear();
                    }
                    datas.addAll(data.getData().getData());
                    mAdapterex.updateData(datas).notifyDataSetChanged();
                    pager++;
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                isLoadingMore = false;
            }
        }, false);
    }

    /**
     * 显示版本更新弹窗
     */
    private void show(final Coommt banBenM) {

        LinearLayout tuichudia = (LinearLayout) getLayoutInflater().inflate(
                R.layout.popu_update, null);
        TextView queding = (TextView) tuichudia
                .findViewById(R.id.tv_gengxin);
        TextView tv_content = (TextView) tuichudia
                .findViewById(R.id.tv_content);
        TextView tv_title = (TextView) tuichudia
                .findViewById(R.id.tv_title);
        tv_title.setText("发现新版本：V " + banBenM.getData().getVersion_number());
        tv_content.setText(banBenM.getData().getContent());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(tuichudia);
        if ("1".equals(banBenM.getData().getIs_force())) {
            builder.setCancelable(false);
        }
        dialog = builder.show();
        queding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(banBenM.getData().getUrl())));

//                downLoadApk(banBenM.getData().getUrl());
            }

        });
    }

    Coommt banBenM;

    /*
      * 从服务器中下载APK
      */
    protected void downLoadApk(final String path) {
        final ProgressDialog pd; // 进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在下载更新...");
        //                                is_force  1强制更新2不强制
        if ("1".equals(banBenM.getData().getIs_force())) {
            pd.setCancelable(false);
        }
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(path, pd);
                    installApk(file);
                    pd.dismiss(); // 结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /*
     * 从服务器下载apk
     */
    public File getFileFromServer(String path, ProgressDialog pd)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
//        if (hasSdcard()) {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        // 获取到文件的大小
        pd.setMax(conn.getContentLength() / 1024 / 1024);
        InputStream is = conn.getInputStream();
        File file = new File(getActivity().getFilesDir(),
                "wdlt.apk");
//            File file = new File(Environment.getExternalStorageDirectory(),
//                    "wdlt.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len;
        int total = 0;
        while ((len = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
            total += len;
            // 获取当前下载量
            pd.setProgress(total / 1024 / 1024);
        }
        fos.close();
        bis.close();
        is.close();
        return file;
//        } else {
//            return null;
//        }
    }

    // 安装apk
    protected void installApk(File file) {
        String[] command = {"chmod", "777", getActivity().getFilesDir() + "/wdlt.apk"};
        ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
            Intent intent = new Intent();
            // 执行动作
            intent.setAction(Intent.ACTION_VIEW);
            // 执行的数据类型
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
                tv_city.setText(amapLocation.getCity());
                CITY=amapLocation.getCity();
        }
    }
}
