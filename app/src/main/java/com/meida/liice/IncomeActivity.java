package com.meida.liice;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.meida.adapter.InComeUserAdapter;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IncomeActivity extends BaseActivity {
    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    InComeUserAdapter adapter;
    private int pager = 1;
    ArrayList<InCome.DataBean.DataBeans> datas = new ArrayList<>();
    private boolean isLoadingMore = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        ButterKnife.bind(this);
        changeTitle("贡献收益用户");
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
        adapter = new InComeUserAdapter(baseContext, R.layout.item_income_user, datas,2);
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.invite_score_list, Const.POST);
        mRequest.add("page",pager);
        getRequest(new CustomHttpListener<InCome>(baseContext, true, InCome.class) {
            @Override
            public void doWork(InCome data, String code) {
                if ("1".equals(data.getCode())) {
                    if(pager==1)
                    {
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
                isLoadingMore=false;
                if(datas.size()==0)
                {
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }

}
