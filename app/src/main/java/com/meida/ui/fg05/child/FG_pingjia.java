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
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.fragment.BaseFragment;
import com.meida.liice.R;
import com.meida.model.PingJiaOrder;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.CommentDetail_A;
import com.meida.ui.fg05.IntegralReward_A;
import com.meida.ui.fg05.WaitComment_A;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.meida.share.Datas.ISRE;

@SuppressLint("ValidFragment")
public class FG_pingjia extends BaseFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    //     0全部 1 待付款 2 待送货安装 3 待评价  4 已评价
    private int mParam1;
    private String mParam2;


    private Adapter_Order adapter;
    private List<PingJiaOrder.DataBean.DatasBean> datas = new ArrayList<>();
    private boolean isLoadingMore;
    private int pager = 1;
    private FragmentActivity baseContext;

    public FG_pingjia() {
        // Required empty public constructor
    }


    public static FG_pingjia newInstance(int param1, String param2) {
        FG_pingjia fragment = new FG_pingjia();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_orders, container, false);
        ButterKnife.bind(this, view);
        baseContext = getActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
            pager = 1;
            datas.clear();
            swipeRefresh.setRefreshing(true);
            getData(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        swipeRefresh.setRefreshing(true);
//        pager=1;
//        getData(true);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_Order(baseContext, R.layout.item_pingjia, datas);
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
        mRequest = NoHttp.createStringRequest(HttpIp.evaluate_list, Const.POST);
        mRequest.add("type", mParam1);
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<PingJiaOrder>(baseContext, true, PingJiaOrder.class) {
            @Override
            public void doWork(PingJiaOrder data, String code) {
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
                    adapter.notifyDataSetChanged();
                    recycleLisst.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    recycleLisst.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                ISRE = 0;
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
    public class Adapter_Order extends CommonAdapter<PingJiaOrder.DataBean.DatasBean> {

        public Adapter_Order(Context context, int layoutId, List<PingJiaOrder.DataBean.DatasBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final PingJiaOrder.DataBean.DatasBean s, final int position) {
            ImageView img = holder.getView(R.id.img_itemo);
            CommonUtil.setimg(baseContext, s.getProduct_logo(), R.drawable.moren, img);
            holder.setText(R.id.tv_title_itemo, s.getProduct_name());
            holder.setText(R.id.tv_note_itemo, s.getProduct_spec());
            holder.setText(R.id.tv_money_itemo, s.getProduct_price());
            holder.setText(R.id.tv_count_itemo, "x" + s.getProduct_num());
            TextView tv_tip = holder.getView(R.id.tv_tip_itemo);//积分打赏
            TextView tv_pingjia = holder.getView(R.id.tv_pingjia_itemo);//去评价
            TextView tv_checkpingjia = holder.getView(R.id.tv_checkpingjia_itemo);//查看评价
            holder.setText(R.id.tv_orderno_itemo, "订单号：" + s.getOrder_id());//订单编号
            String str_type = "";
//评价状态1未评价2已评价
            switch (s.getComment_status()) {//状态0或空为全部，1已取消2待支付3待送装4待确认5已完成
                case "2":
                    holder.setText(R.id.tv_state_itemo, "已评价");//订单状态
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_pingjia.setVisibility(View.GONE);
                    tv_checkpingjia.setVisibility(View.VISIBLE);
                    break;
                case "1":
                    holder.setText(R.id.tv_state_itemo, "待评价");//订单状态
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_pingjia.setVisibility(View.VISIBLE);
                    tv_checkpingjia.setVisibility(View.GONE);
                    break;
            }
            //富文本显示
            String name1 = "共" + s.getProduct_num() + "件商品,实付：";
            String name2 = "￥ " + (Double.parseDouble(s.getProduct_price()) * Integer.parseInt(s.getProduct_num()));
            String name3 = "";
            String str = name1 + name2 + name3;
            final SpannableStringBuilder sp = new SpannableStringBuilder(str);
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.txthui2)), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.org)), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.txthui2)), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            //        这个 SpannableStringBuilder 如果转为 tostring 就没有作用了！
            TextView tv_info = holder.getView(R.id.tv_info_itemo);//商品备注
            tv_info.setText(sp);
            tv_tip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "积分打赏");
                    baseContext.startActivity(new Intent(baseContext, IntegralReward_A.class)
                            .putExtra("id", s.getOrder_id())
                            .putExtra("type", "1")
                    );

                }
            });
            tv_pingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "去评价");
                    baseContext.startActivity(new Intent(baseContext, WaitComment_A.class)
                            .putExtra("id", s.getOrder_id())
                            .putExtra("pid", s.getProduct_id())
                            .putExtra("img", s.getProduct_logo())
                            .putExtra("title", s.getProduct_name())
                            .putExtra("type", "1")
                            .putExtra("content", s.getProduct_spec())
                    );
                }
            });
            tv_checkpingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "查看评价");
                    baseContext.startActivity(new Intent(baseContext, CommentDetail_A.class)
                            .putExtra("pid", s.getProduct_id())
                            .putExtra("id", s.getOrder_id())
                    );

                }
            });

        }
    }

}
