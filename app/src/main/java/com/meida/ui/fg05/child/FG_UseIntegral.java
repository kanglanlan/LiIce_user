package com.meida.ui.fg05.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.fragment.BaseFragment;
import com.meida.liice.JiFenDuiHuanActivity;
import com.meida.liice.R;
import com.meida.model.Goods;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 花积分
 *
 * @author Administrator-LFC
 *         created at 2018/8/13 15:26
 */@SuppressLint("ValidFragment")

public class FG_UseIntegral extends BaseFragment {


    @Bind(R.id.recycle_lisst)
    RecyclerView recycleLisst;
    @Bind(R.id.empty_img)
    ImageView emptyImg;
    @Bind(R.id.empty_hint)
    TextView emptyHint;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private Adapter_UseIntegral adapter;
    private FragmentActivity baseContext;
    private int pager = 1;
    ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<>();
    public FG_UseIntegral() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_use_integral, container, false);
        ButterKnife.bind(this, view);
        baseContext = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getData(true);
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recycleLisst.setLayoutManager(gridLayoutManager);
        adapter = new Adapter_UseIntegral(baseContext, R.layout.item_useintegral, datas);
        recycleLisst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                pageNum = 1;
                getData(false);
            }
        });
        recycleLisst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = gridLayoutManager.getItemCount();
                int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                Log.e("--lfc", "lastVisibleItem: " + lastVisibleItem + " total-3 : " + (total - 3) + "  dy:" + dy);
                if (lastVisibleItem >= total - 3 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        pageNum++;
                        getData(false);

                    }
                }

            }
        });

    }

    /**
     * 这个接口 可以的话  一页数据尽可能的多一点
     * 避免recyclerview 上拉加载失效
     * 原因： CoordinatoLayout 滑动内部处理了
     * 解决 ： 一页数据尽可能的排满整个屏幕 使recyclerview  可滑动
     *
     * @author Administrator-LFC
     * created at 2018/8/14 10:51
     */
    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.goodslist, Const.POST);
        mRequest.add("type","2");	//1商品2兑换商品
        mRequest.add("page",pager);
        getRequest(new CustomHttpListener<Goods>(getActivity(), true, Goods.class) {
            @Override
            public void doWork(Goods data, String code) {
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
            }
        }, false);

    }

    private void initEmpty(boolean isEmpty) {
        if (isEmpty) {
            recycleLisst.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recycleLisst.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 花积分
     */
    public class Adapter_UseIntegral extends CommonAdapter<Goods.DataBean.DataBeans> {

        public Adapter_UseIntegral(Context context, int layoutId, List<Goods.DataBean.DataBeans> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder,final Goods.DataBean.DataBeans s, final int position) {
            ImageView img_info = (ImageView) holder.getView(R.id.img_itemui);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) img_info.getLayoutParams();
            lp.width = (CommonUtil.getScreenWidth(baseContext) - 6 * CommonUtil.dip2px(baseContext, 10)) / 3;
            lp.height = lp.width;
            img_info.setLayoutParams(lp);
            CommonUtil.setimg(baseContext, s.getLogo(), R.drawable.moren, img_info);
            holder.setText(R.id.tv_name_itemui, s.getTitle());
            holder.setText(R.id.tv_change_itmeui, "兑换积分：" + s.getSales_score_exchange());
            holder.setText(R.id.tv_subsidy_itemui, "补贴积分：" + s.getSales_score());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, JiFenDuiHuanActivity.class).putExtra("id",s.getId()));
                }
            });
        }
    }


}
