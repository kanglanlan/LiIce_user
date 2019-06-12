package com.meida.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.meida.adapter.PingJiaAdapter;
import com.meida.liice.R;
import com.meida.model.PingJiaList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class pingjiafragment extends BaseFragment {

    String id;
    String tyop;

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    PingJiaAdapter adapter;
    @Bind(R.id.rb_quanbu)
    RadioButton rbQuanbu;
    @Bind(R.id.rb_youtu)
    RadioButton rbYoutu;
    private int pager = 1;
    ArrayList<PingJiaList.DataBean.DataBeans> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    //调用这个方法切换时不会释放掉Fragment
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    public pingjiafragment(String id,String tyop) {
        this.id = id;
        this.tyop = tyop;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pingjiafragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        rbQuanbu.performClick();
        Nonce.getcommentnum(id,getActivity(), new Nonce.StringtwoCallback() {
            @Override
            public void doWork(String string1, String string2) {
                        rbQuanbu.setText("全部(" +string1+ ")");
                        rbYoutu.setText("有图(" + string2+ ")");

            }
        });
    }

    private void initview() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
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
        adapter = new PingJiaAdapter(getActivity(), R.layout.item_pj, datas);
        recycleList.setAdapter(adapter);
    }


    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.goodsComment, Const.POST);
        mRequest.add("type", tyop);    //1商品2兑换商品
        mRequest.add("product_id", id);
        if (rbQuanbu.isChecked()) {
            mRequest.add("comment_type", 1);
        } else {
            mRequest.add("comment_type", 2);
        }
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<PingJiaList>(getActivity(), true, PingJiaList.class) {
            @Override
            public void doWork(PingJiaList data, String code) {
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
                if(datas.size()==0)
                {
                    adapter.notifyDataSetChanged();
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rb_quanbu, R.id.rb_youtu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_quanbu:
                pager = 1;
                datas.clear();
                swipeRefresh.setRefreshing(true);
                getdata(false);
                break;
            case R.id.rb_youtu:
                pager = 1;
                datas.clear();
                swipeRefresh.setRefreshing(true);
                getdata(false);
                break;
        }
    }
}
