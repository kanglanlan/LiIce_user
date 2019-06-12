package com.meida.ui.fg05.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.fragment.BaseFragment;
import com.meida.liice.R;
import com.meida.liice.ReportsDetail_Activity;
import com.meida.model.BaoBeiList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.ReportsDetail_A;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 报备的项目 Fragment
 *
 * @author Administrator-LFC
 *         created at 2018/8/14 17:31
 */
@SuppressLint("ValidFragment")
public class FG_Reports extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
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

    //    报备项目 类型标示：  0 全部  1 成功 2 失败  分类 1失败8成功9全部
    private String mParam1;
    private Adapter_Report adapter;
    private List<String> mlist_data = new ArrayList<>();
    private FragmentActivity baseContext;
    private int pager = 1;
    ArrayList<BaoBeiList.DataBean.DataBeasn> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    public FG_Reports() {
        // Required empty public constructor
    }


    public static FG_Reports newInstance(String param1, String param2) {
        FG_Reports fragment = new FG_Reports();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_reports, container, false);
        ButterKnife.bind(this, view);
        baseContext = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        swipeRefresh.setRefreshing(true);
        getData(true);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_Report(baseContext, R.layout.item_reports, datas);
        recycleLisst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                getData(false);
            }
        });
        recycleLisst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                Log.e("--lfc", "lastVisibleItem: " + lastVisibleItem + " total-3 : " + (total - 3) + "  dy:" + dy);
                if (lastVisibleItem >= total - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getData(false);
                    }
                }

            }
        });


    }

    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.projectlist, Const.POST);
        mRequest.add("type", mParam1);
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<BaoBeiList>(baseContext, true, BaoBeiList.class) {
            @Override
            public void doWork(BaoBeiList data, String code) {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 报备的项目
     */
    public class Adapter_Report extends CommonAdapter<BaoBeiList.DataBean.DataBeasn> {

        public Adapter_Report(Context context, int layoutId, List<BaoBeiList.DataBean.DataBeasn> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final BaoBeiList.DataBean.DataBeasn s, final int position) {
            holder.setText(R.id.tv_time_itemr, s.getCreate_time());//时间
            holder.setText(R.id.tv_pnum_itemr, s.getTransaction_number());//项目编号
            holder.setText(R.id.tv_pname_itemr, s.getTitle());//项目名称
            holder.setText(R.id.tv_ptype_itemr, s.getProperty_name());//项目类型
            holder.setText(R.id.tv_parea_itemr, s.getArea() + "平方米");//项目面积
            holder.setText(R.id.tv_ownername_itemr, s.getAddress_name());//业主姓名
            holder.setText(R.id.tv_ownertel_itemr, s.getAddress_tel());//业主电话
            holder.setText(R.id.tv_fromname_itemr, s.getUser_real_name());//推荐人
            holder.setText(R.id.tv_intent_itemr, s.getBrand_name());//意向品牌

            holder.getView(R.id.tv_pdetail_itemr).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("1".equals(s.getStatus())) {
                        baseContext.startActivity(new Intent(baseContext, ReportsDetail_A.class).putExtra("id", s.getId()));
                    } else {
                        baseContext.startActivity(new Intent(baseContext, ReportsDetail_Activity.class).putExtra("id", s.getId()));
                    }
                }
            });
        }
    }


}
