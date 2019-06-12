package com.meida.liice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.adapter.BuTieAdapter;
import com.meida.model.FenLeiList;
import com.meida.model.Goods;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PopupWindowUtils;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuTieKongTiaoActivity extends BaseActivity {

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tv_cartnum)
    TextView tv_cartnum;


    BuTieAdapter adapter;
    @Bind(R.id.view_xian)
    View view_xian;
    @Bind(R.id.fl_gw)
    FrameLayout fl_gw;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_liebie)
    TextView tvLiebie;
    @Bind(R.id.tv_jiage)
    TextView tvJiage;
    @Bind(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    private int pager = 1;
    ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<>();
    private boolean isLoadingMore = false;
    private String user_price = "";
    private String user_sales_num = "";
    private String product_type_id = "";
    private Drawable top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_tie_kong_tiao);
        ButterKnife.bind(this);
        changeTitle("补贴空调");
        initview();
        getdata(false);
        getdatas(1);
    }

    /**
     * 示例 1商品分类2 对应品牌  3 对应项目类型 4对应意向空调类型 6 银行卡 101商品标签 102商品规格 1001项目规模）
     */
    FenLeiList leiList;

    public void getdatas(final int type) {
        Nonce.getpinpai(false, 1, baseContext, new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                leiList = data;
                if (type == 2) {
                    showpopu();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PreferencesUtils.getInt(baseContext, "login") == 1) {
            Nonce.getcartnum(baseContext, new Nonce.StringCallback() {
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

    private void initview() {
        fl_gw.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleList.setLayoutManager(linearLayoutManager);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.main));
        recycleList.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                datas.clear();
                adapter.notifyDataSetChanged();
                pager = 1;
                getdata(false);
            }
        });
        swipeRefresh.setRefreshing(true);
        recycleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                if (lastVisibleItem >= total - 3 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getdata(false);
                    }
                }
            }
        });
        adapter = new BuTieAdapter(baseContext, R.layout.item_butieshangpin, datas);
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.goodslist, Const.POST);
        mRequest.add("type", "1");    //1商品2兑换商品
        mRequest.add("page", pager);
        mRequest.add("product_type_id", product_type_id);
        mRequest.add("user_price", user_price);
        mRequest.add("user_sales_num", user_sales_num);
        getRequest(new CustomHttpListener<Goods>(baseContext, true, Goods.class) {
            @Override
            public void doWork(Goods data, String code) {
                if ("1".equals(data.getCode())) {
                    if (pager == 1) {
                        datas.clear();
                    }
                    datas.addAll(data.getData().getData());
                    adapter.notifyDataSetChanged();
                    pager++;
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                isLoadingMore = false;
                if (datas.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }

    @OnClick({R.id.img_title_rigth, R.id.ll_leibie, R.id.ll_jiage, R.id.tv_xiaoliang, R.id.fl_gw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_gw:
                if (PreferencesUtils.getInt(baseContext, "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    return;
                }
                StartActivity(GouWuCheActivity.class);
                break;
            case R.id.img_title_rigth:
                break;
            case R.id.ll_leibie:
                top = getResources().getDrawable(R.mipmap.ico_lb013);// 获取res下的图片drawable
                tvLiebie.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
                tvXiaoliang.setTextColor(getResources().getColor(R.color.black));
                tvLiebie.setTextColor(getResources().getColor(R.color.main));
                tvJiage.setTextColor(getResources().getColor(R.color.black));
                if (leiList == null) {
                    getdatas(2);
                } else {
                    showpopu();
                }
                break;
            case R.id.ll_jiage:
                tvXiaoliang.setTextColor(getResources().getColor(R.color.black));
                tvLiebie.setTextColor(getResources().getColor(R.color.black));
                top = getResources().getDrawable(R.mipmap.ico_lb012);// 获取res下的图片drawable
                tvLiebie.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
                tvJiage.setTextColor(getResources().getColor(R.color.main));
                if (TextUtils.isEmpty(user_price) || "asc".equals(user_price)) {
                    user_price = "desc";
                    top = getResources().getDrawable(R.mipmap.ico_lb015);// 获取res下的图片drawable
                    tvJiage.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
                } else {
                    top = getResources().getDrawable(R.mipmap.ico_lb014);// 获取res下的图片drawable
                    tvJiage.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
                    user_price = "asc";
                }
                user_sales_num = "";
                pager = 1;
                swipeRefresh.setRefreshing(true);
                getdata(false);
                break;
            case R.id.tv_xiaoliang:
                top = getResources().getDrawable(R.mipmap.ico_lb012);// 获取res下的图片drawable
                tvLiebie.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
                tvXiaoliang.setTextColor(getResources().getColor(R.color.main));
                tvLiebie.setTextColor(getResources().getColor(R.color.black));
                tvJiage.setTextColor(getResources().getColor(R.color.black));
                if (TextUtils.isEmpty(user_sales_num) || "asc".equals(user_sales_num)) {
                    user_sales_num = "desc";
                } else {
                    user_sales_num = "asc";
                }
                user_price = "";
                pager = 1;
                swipeRefresh.setRefreshing(true);
                getdata(false);
                break;
        }
    }

    public void showpopu() {

        PopupWindowUtils.populist(this, view_xian, (ArrayList<FenLeiList.DataBean.ListBean>) leiList.getData().getList(),
                1, new PopupWindowUtils.PopupWindowCallBack() {
                    @Override
                    public void doWork(String id) {
                        product_type_id = id;
                        pager = 1;
                        swipeRefresh.setRefreshing(true);
                        getdata(false);
                    }

                });
    }
}
