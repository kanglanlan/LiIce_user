package com.meida.liice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.model.FuWuShangList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XuanZeFuWuShangActivity extends BaseActivity {
    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    FuWuShangAdapter adapter;
    private int pager = 1;
    ArrayList<FuWuShangList.DataBean.DatasBean> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_ze_fu_wu_shang);
        ButterKnife.bind(this);
        changeTitle("选择服务商");
        initview();
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
        adapter = new FuWuShangAdapter(baseContext, R.layout.item_fuwushang, datas);
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.merchant_list, Const.POST);
        mRequest.add("city_id", getIntent().getStringExtra("city_id"));
        mRequest.add("area_id", getIntent().getStringExtra("area_id"));
        mRequest.add("product_info", getIntent().getStringExtra("product_info"));
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<FuWuShangList>(baseContext, true, FuWuShangList.class) {
            @Override
            public void doWork(FuWuShangList data, String code) {
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
            }
        }, false);
    }

    public class FuWuShangAdapter extends CommonAdapter<FuWuShangList.DataBean.DatasBean> {
        private ArrayList<FuWuShangList.DataBean.DatasBean> datas = new ArrayList<FuWuShangList.DataBean.DatasBean>();
        Context mContext;

        public FuWuShangAdapter(Context context, int layoutId, ArrayList<FuWuShangList.DataBean.DatasBean> datas) {
            super(context, layoutId, datas);
            this.datas = datas;
            mContext = context;
        }

        @Override
        public void convert(final ViewHolder holder, final FuWuShangList.DataBean.DatasBean s, int position) {
            holder.setText(R.id.tv_fuwushang, s.getNickname());
            TextView textView = holder.getView(R.id.tv_status);
            //商户类型；1核心服务商 2服务商
            if ("1".equals(s.getMerchant_type())) {
                holder.setText(R.id.tv_type, "核心服务商");
            }
            if ("2".equals(s.getMerchant_type())) {
                holder.setText(R.id.tv_type, "服务商");
            }

            holder.setText(R.id.tv_add, s.getArea_merger_name() + s.getCompany_address());
//        服务状态：1正常2忙碌
            if ("1".equals(s.getMerchant_service_status())) {
                textView.setText("预约");
                textView.setBackgroundResource(R.drawable.loginbt);
//              stock_status  0库存不足，1库存充足
//                if ("1".equals(s.getStock_status())) {
//                    textView.setText("预约");
//                    textView.setBackgroundResource(R.drawable.loginbt);
////              stock_status  0库存不足，1库存充足
//                } else {
//                    textView.setText("繁忙");
//                    textView.setBackgroundResource(R.drawable.huibt);
//                }
            } else {
                textView.setText("繁忙");
                textView.setBackgroundResource(R.drawable.huibt);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("1".equals(s.getMerchant_service_status())) {
                        if ("1".equals(s.getStock_status())) {
                            Intent intent = new Intent();
                            intent.putExtra("id", s.getId());
                            intent.putExtra("name", s.getNickname());
                            setResult(RESULT_OK, intent);
                            finish();
//              stock_status  0库存不足，1库存充足
                        } else {
                            Nonce.Sendpush(s.getId(), baseContext);
                            showtoa("库存不足，请选择其他服务商");
                        }
                    } else {
                        showtoa("该服务商暂不能选择");
                    }
                }
            });

        }

    }
}
