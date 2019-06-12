package com.meida.liice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.meida.model.Cars;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GouWuCheActivity extends BaseActivity {

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.ll_di)
    LinearLayout ll_di;
    @Bind(R.id.ll_dibu)
    LinearLayout ll_dibu;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.cb_quanxuan)
    CheckBox cbQuanxuan;
    @Bind(R.id.tv_zongjia)
    TextView tvZongjia;
    @Bind(R.id.tv_butie)
    TextView tvButie;
    @Bind(R.id.tv_jiesuan)
    TextView tvJiesuan;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    public static Activity gouwu;
    GouWuCheAdapter adapter;
    private int pager = 1;
    ArrayList<Cars.DataBean.ListBean> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
        ButterKnife.bind(this);
        changeTitle("购物车");
        gouwu = this;
        initview();
        getdata(false);
        cbQuanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbQuanxuan.isChecked()) {
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setCheck(1);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setCheck(0);
                    }
                    adapter.notifyDataSetChanged();
                }
                jisuan();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pager = 1;
        datas.clear();
        getdata(false);
    }

    private void initview() {
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleList.setLayoutManager(linearLayoutManager);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.main));
        recycleList.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cbQuanxuan.setChecked(false);
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
                if (lastVisibleItem >= total - 2 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getdata(false);
                    }
                }
            }
        });
        adapter = new GouWuCheAdapter(baseContext, R.layout.item_gouwuche, datas);
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.getshopCart, Const.POST);
        getRequest(new CustomHttpListener<Cars>(baseContext, true, Cars.class) {
            @Override
            public void doWork(Cars data, String code) {
                if ("1".equals(data.getCode())) {
                    if (pager == 1) {
                        datas.clear();
                    }
                    datas.addAll(data.getData().getList());
                    adapter.notifyDataSetChanged();
                    if (pager == 1) {
                        jisuan();
                    }
                    pager++;
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                isLoadingMore = false;
                if (datas.size() == 0) {
                    ll_di.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    emptyView.setVisibility(View.VISIBLE);
                    tvTitleRight.setVisibility(View.GONE);
                } else {
                    tvTitleRight.setVisibility(View.VISIBLE);
                    tvTitleRight.setText("编辑");
                    ll_di.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }


    @OnClick({R.id.tv_title_right, R.id.tv_jiesuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                if (tvTitleRight.getText().toString().equals("编辑")) {
                    tvTitleRight.setText("完成");
                    ll_dibu.setVisibility(View.INVISIBLE);
                    tvJiesuan.setText("删除");
                } else {
                    tvTitleRight.setText("编辑");
                    ll_dibu.setVisibility(View.VISIBLE);
                    tvJiesuan.setText("去结算");
                }
                break;
            case R.id.tv_jiesuan:
                if (tvTitleRight.getText().toString().equals("编辑")) {
                    jisuan();
                    if (zongjia == 0) {
                        showtoa("请选择");
                        return;
                    }
                    Intent intent = new Intent(baseContext, QueRenOrderActivity.class);
                    Bundle bundleObject = new Bundle();
                    bundleObject.putSerializable("list", datas);
                    intent.putExtras(bundleObject);
                    intent.putExtra("qian", tvZongjia.getText().toString());
                    intent.putExtra("allnums", allnums + "");
                    startActivity(intent);
                } else {
                    final NormalDialog dialog = new NormalDialog(this);
                    dialog.content("确定删除")
                            .showAnim(new BounceTopEnter())
                            .dismissAnim(new SlideBottomExit())
                            .show();
                    dialog.setOnBtnClickL(
                            new OnBtnClickL() {
                                @Override
                                public void onBtnClick() {
                                    Log.i("=======", "点击取消");
                                    dialog.dismiss();
                                }
                            },
                            new OnBtnClickL() {
                                @Override
                                public void onBtnClick() {
                                    dialog.dismiss();
                                    jisuan();
                                    if (TextUtils.isEmpty(ids)) {
                                        showtoa("请选择商品");
                                        return;
                                    }
                                    delete();
                                }
                            });
                }
                break;
        }
    }

    /**
     * 删除购物车
     */
    private void delete() {

        mRequest = NoHttp.createStringRequest(HttpIp.delshopCart, Const.POST);
        mRequest.add("ids", ids);
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    pager = 1;
                    swipeRefresh.setRefreshing(true);
                    getdata(false);
                }
            }
        }, true);
    }

    public class GouWuCheAdapter extends CommonAdapter<Cars.DataBean.ListBean> {
        private ArrayList<Cars.DataBean.ListBean> datas = new ArrayList<Cars.DataBean.ListBean>();
        Context mContext;

        public GouWuCheAdapter(Context context, int layoutId, ArrayList<Cars.DataBean.ListBean> datas) {
            super(context, layoutId, datas);
            this.datas = datas;
            mContext = context;
        }

        @Override
        public void convert(final ViewHolder holder, final Cars.DataBean.ListBean s, int position) {
            ImageView xz = holder.getView(R.id.img_xz);
            ImageView img = holder.getView(R.id.img_beihuo);
            CommonUtil.setimg(mContext, s.getLogo(), R.drawable.moren, img);
            holder.setText(R.id.tv_name, s.getTitle());
            holder.setText(R.id.tv_type, s.getContent());
            holder.setText(R.id.tv_nums, s.getProduct_num());
            holder.setText(R.id.tv_yajin, "¥" + s.getPrice());
            if (s.getCheck() == 1) {
                xz.setImageResource(R.drawable.ico_lb027);
            } else {
                xz.setImageResource(R.drawable.ico_lb026);
            }
            xz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s.getCheck() == 1) {
                        s.setCheck(0);
                    } else {
                        s.setCheck(1);
                    }
                    jisuan();
                    notifyDataSetChanged();
                }
            });
            holder.getView(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CommonUtil.getNetworkState(baseContext) == 0) {
                        return;
                    }
                    int num = Integer.parseInt(s.getProduct_num());
                    ++num;

                    Nonce.changecart(s.getProduct_id(), num + "", s.getId(), baseContext, new Nonce.StringCallback() {
                        @Override
                        public void doWork(String string) {
                            s.setProduct_num((Integer.parseInt(s.getProduct_num()) + 1) + "");
                            notifyDataSetChanged();
                        }
                    });
                }
            });
            holder.getView(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CommonUtil.getNetworkState(baseContext) == 0) {
                        return;
                    }
                    int num = Integer.parseInt(s.getProduct_num());
                    if (num == 1) {
                        return;
                    }
                    --num;
                    Nonce.changecart(s.getProduct_id(), num + "", s.getId(), baseContext, new Nonce.StringCallback() {
                        @Override
                        public void doWork(String string) {
                            s.setProduct_num((Integer.parseInt(s.getProduct_num()) - 1) + "");
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    /**
     * 编辑购物车
     */
    private void changenum() {
        mRequest = NoHttp.createStringRequest(HttpIp.delshopCart, Const.POST);
        mRequest.add("ids", ids);
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    pager = 1;
                    swipeRefresh.setRefreshing(true);
                    getdata(false);
                }
            }
        }, true);

    }

    double zongjia, jifen;
    String ids = "";
    int allnums;

    public void jisuan() {
        StringBuffer buffer = new StringBuffer("");
        zongjia = 0;
        jifen = 0;
        allnums = 0;
        int num = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getCheck() == 1) {
                ++num;
                zongjia = Double.parseDouble(datas.get(i).getPrice()) * Integer.parseInt(datas.get(i).getProduct_num()) + zongjia;
                jifen = Double.parseDouble(datas.get(i).getUser_score()) * Integer.parseInt(datas.get(i).getProduct_num()) + jifen;
                allnums = allnums + Integer.parseInt(datas.get(i).getProduct_num());
                buffer.append(datas.get(i).getId());
                buffer.append(",");
            }
        }
        if (datas.size() == 0) {
            cbQuanxuan.setChecked(false);
        } else {
            if (num == datas.size()) {
                cbQuanxuan.setChecked(true);
            } else {
                cbQuanxuan.setChecked(false);
            }
        }
        if (!TextUtils.isEmpty(buffer.toString())) {
            buffer.delete(buffer.length() - 1, buffer.length());
            ids = buffer.toString();
        } else {
            ids = "";
        }
        tvZongjia.setText(zongjia + "");
        tvButie.setText("补贴积分" + jifen + "积分");
    }
}
