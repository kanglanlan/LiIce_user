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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.fragment.BaseFragment;
import com.meida.liice.R;
import com.meida.model.WeiXiuList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.ServiceDetail_A;
import com.meida.ui.fg05.WaitComment_A;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 维修记录 Fragment
 *
 * @author Administrator-LFC
 *         created at 2018/8/15 17:06
 */
@SuppressLint("ValidFragment")

public class FG_ServiceRecord extends BaseFragment {

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
    private boolean isPrepared = false;
    //    维修记录  类型标示：  0 全部  1 待维修 2 已完成
    private Adapter_SR adapter;
    private List<WeiXiuList.DataBean.DataBsean> datas = new ArrayList<>();
    private int pager = 1;
    private FragmentActivity baseContext;
    String type;
    String test;

    public FG_ServiceRecord(String type) {
        this.type = type;
    }

    //调用这个方法切换时不会释放掉Fragment
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isPrepared && isVisibleToUser) {
            //加载数据
            test = type;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_service_record, container, false);
        ButterKnife.bind(this, view);
        baseContext = getActivity();
        isPrepared = true;
        setUserVisibleHint(getUserVisibleHint());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        pager = 1;
        initView();
        getData(true);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_SR(baseContext, R.layout.item_servicerecord, datas);
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
        mRequest = NoHttp.createStringRequest(HttpIp.apply_list, Const.POST);
        mRequest.add("user_id", PreferencesUtils.getString(getActivity(), "uid"));
        mRequest.add("status", type);
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<WeiXiuList>(getActivity(), true, WeiXiuList.class) {
            @Override
            public void doWork(WeiXiuList data, String code) {
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
                    adapter.notifyDataSetChanged();
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
    public class Adapter_SR extends CommonAdapter<WeiXiuList.DataBean.DataBsean> {

        public Adapter_SR(Context context, int layoutId, List<WeiXiuList.DataBean.DataBsean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final WeiXiuList.DataBean.DataBsean s, final int position) {

            holder.setText(R.id.tv_applytime_itemsr, "申请时间：" + s.getCreate_time());//时间

            TextView tv_state = holder.getView(R.id.tv_state_itemsr);
            TextView tv_pingjia = holder.getView(R.id.tv_pingjia_itemsr);
            String str_type = "";
//            判断维修状态
            //	0全部；1已取消2待维修，3已完成
            switch (s.getStatus()) {
                case "2":
                    tv_state.setText("待维修");//维修状态 = "待维修";
                    tv_state.setTextColor(baseContext.getResources().getColor(R.color.cheng));
                    tv_pingjia.setVisibility(View.GONE);
                    break;
                case "3":
                    tv_state.setText("已完成");
                    tv_state.setTextColor(baseContext.getResources().getColor(R.color.txthui));
                    tv_pingjia.setVisibility(View.VISIBLE);
                    switch (s.getComment_status()) {
                        case "2":
                            tv_pingjia.setVisibility(View.GONE);
                            break;
                        case "1":
                            tv_pingjia.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
                default:
                    break;
            }
            //	comment_status  1是未评价  2是已评价



            holder.setText(R.id.tv_ownername_itemsr, s.getAddress_name());//报修人
            holder.setText(R.id.tv_tel_itemsr, s.getAddress_tel());//报修电话
            holder.setText(R.id.tv_type_itemsr, s.getProduct_info().getProduct_spec());//维修机型
            holder.setText(R.id.tv_note_itemsr, s.getUser_content());//备注
            if (TextUtils.isEmpty(s.getUser_content())) {
                holder.getView(R.id.ll_beizhu).setVisibility(View.GONE);
            } else {
                holder.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
            }
            holder.setText(R.id.tv_servicetime_itemsr, s.getAddress_service_time());//上门时间

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "跳转到维修记录详情");
                    /**
                     Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                     intent.putExtra("person_data", person);
                     startActivity(intent);
                     */
                    startActivity(new Intent(baseContext, ServiceDetail_A.class).putExtra("info", s));
                }
            });
            tv_pingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(baseContext, WaitComment_A.class)
                            .putExtra("type", "3")
                            .putExtra("id", s.getOrder_id())
                            .putExtra("pid", s.getProduct_id())
                            .putExtra("transaction_number", s.getTransaction_number())
                    );


                }
            });
        }
    }

}
