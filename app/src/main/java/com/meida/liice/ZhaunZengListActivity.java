package com.meida.liice;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.meida.adapter.IntegerlAdapter;
import com.meida.model.JiFenList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZhaunZengListActivity extends BaseActivity {

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    IntegerlAdapter adapter;
    private int pager = 1;
    ArrayList<JiFenList.DataBean.DataBsean> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaun_zeng_list);
        ButterKnife.bind(this);
        if ("1".equals(getIntent().getStringExtra("tag"))) {
            changeTitle("转赠记录");
        } else {
            changeTitle("提现记录");
        }
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
        if ("1".equals(getIntent().getStringExtra("tag"))) {
            adapter = new IntegerlAdapter(baseContext, "3", R.layout.item_shouru, datas);
        } else {
            adapter = new IntegerlAdapter(baseContext, "2", R.layout.item_shouru, datas);
        }
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        if ("1".equals(getIntent().getStringExtra("tag"))) {//("转赠记录")
            mRequest = NoHttp.createStringRequest(HttpIp.transfer_list, Const.POST);
        } else {// "提现明细"
            mRequest = NoHttp.createStringRequest(HttpIp.withdrawindex, Const.POST);
        }
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<JiFenList>(baseContext, true, JiFenList.class) {
            @Override
            public void doWork(JiFenList data, String code) {
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
}
