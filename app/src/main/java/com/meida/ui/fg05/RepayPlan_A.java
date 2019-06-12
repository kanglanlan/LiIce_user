package com.meida.ui.fg05;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.XiangMuInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 还款计划
 *
 * @author Administrator-LFC
 *         created at 2018/8/15 9:43
 */
public class RepayPlan_A extends BaseActivity {

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleLisst;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    private Adapter_Plan adapter;
    ArrayList<XiangMuInfo.DataBean.PaymentListBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_plan);
        ButterKnife.bind(this);
        changeTitle("还款计划");
        Bundle bundleObject = getIntent().getExtras();
        datas = (ArrayList<XiangMuInfo.DataBean.PaymentListBean>) bundleObject.getSerializable("list");
        if (datas.size() == 0) {
            recycleLisst.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recycleLisst.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        initView();
    }

    private void initView() {
        changeTitle("还款计划");
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_Plan(baseContext, R.layout.item_repay_plan, datas);
        recycleLisst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
            }
        });
    }


    /**
     * 还款计划
     */
    public class Adapter_Plan extends CommonAdapter<XiangMuInfo.DataBean.PaymentListBean> {

        public Adapter_Plan(Context context, int layoutId, List<XiangMuInfo.DataBean.PaymentListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, XiangMuInfo.DataBean.PaymentListBean s, final int position) {
            TextView tv_line_top = holder.getView(R.id.tv_linetop_itemrp); // 上边的线
            TextView tv_line_bottom = holder.getView(R.id.tv_linebottom_itemrp);// 下边的线
            ImageView img_tag = holder.getView(R.id.img_tag_itemrp); //图片

            if (datas.size() == 1) {
                tv_line_top.setVisibility(View.INVISIBLE);
                tv_line_bottom.setVisibility(View.INVISIBLE);
            } else {
                if (position == 0) {
                    tv_line_top.setVisibility(View.INVISIBLE);
                    tv_line_bottom.setVisibility(View.VISIBLE);
                } else if (position == datas.size() - 1) {
                    tv_line_top.setVisibility(View.VISIBLE);
                    tv_line_bottom.setVisibility(View.INVISIBLE);
                } else {
                    tv_line_top.setVisibility(View.VISIBLE);
                    tv_line_bottom.setVisibility(View.VISIBLE);
                }
            }
            // TODO: 2018/8/15  根据状态判断 颜色
            if ("2".equals(s.getStatus())) {
                tv_line_top.setBackgroundResource(R.color.org);
                tv_line_bottom.setBackgroundResource(R.color.org);
                img_tag.setImageResource(R.mipmap.ico_lb091); //选中的图标
            } else {
                tv_line_top.setBackgroundResource(R.color.txthui);
                tv_line_bottom.setBackgroundResource(R.color.txthui);
                img_tag.setImageResource(R.mipmap.ico_lb092); //未选中的图标

            }

//            付款状态：1未付款；2已付款
            if ("1".equals(s.getStatus())) {
                holder.setText(R.id.tv_type_itemrp, "待还款");//还款日期
            }
            if ("2".equals(s.getStatus())) {
                holder.setText(R.id.tv_type_itemrp, "已还款");//还款日期
            }
            holder.setText(R.id.tv_time_itemrp, s.getBack_time());//还款日期
            holder.setText(R.id.tv_money_itemrp, s.getAmount() + "元");//金额
            holder.setText(R.id.tv_step_itemrp, s.getCurrent_times() + "/" + s.getTotal_times() + "期");//期数
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
