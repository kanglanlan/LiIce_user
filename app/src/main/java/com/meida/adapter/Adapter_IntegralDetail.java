package com.meida.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.liice.R;
import com.meida.model.JiFenList;
import com.meida.ui.fg05.IntegralDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分明细
 */
public class Adapter_IntegralDetail extends CommonAdapter<JiFenList.DataBean.DataBsean> {

    private List<JiFenList.DataBean.DataBsean> list_data = new ArrayList<>();
    private Activity baseContext = null;
    //  1 收入  2 支出
    private int Type = 1;

    public Adapter_IntegralDetail(Activity context, List<JiFenList.DataBean.DataBsean> datas, int type) {
        super(context, R.layout.item_integral_detail, datas);
        baseContext = context;
        list_data.clear();
        list_data.addAll(datas);
        Type = type;
    }

    public void setList_data(List<JiFenList.DataBean.DataBsean> list_data) {
        this.list_data = list_data;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, final JiFenList.DataBean.DataBsean s, final int position) {
//底线 显示
        LinearLayout line = holder.getView(R.id.line_itemid);
        line.setVisibility((position == list_data.size() - 1) ? View.GONE : View.VISIBLE);


        holder.setText(R.id.tv_title_itemid, s.getType_name());
        holder.setText(R.id.tv_num_itemid, "交易编号：" + s.getTransaction_number());
        holder.setText(R.id.tv_time_itemid, "交易时间：" + s.getCreate_time());

        TextView tv_tag = holder.getView(R.id.tv_tag_itemid);
        if (Type == 1) {
            holder.setText(R.id.tv_money_itemid, "+" + s.getScore());
            tv_tag.setVisibility(View.VISIBLE);
            // TODO: 2018/8/14  判断 是可提现 还是可兑换
            if (!"可兑换".equals(s.getScore_type_name())) {
                tv_tag.setBackgroundResource(R.drawable.bg_s_orange);
            } else {
                tv_tag.setBackgroundResource(R.drawable.bg_s_blue);
            }
            tv_tag.setText(s.getScore_type_name());
        } else {
            holder.setText(R.id.tv_money_itemid, "-" + s.getScore());
            tv_tag.setVisibility(View.INVISIBLE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonUtil.showToask(baseContext, position + "");
                mContext.startActivity(new Intent(mContext, IntegralDetailActivity.class)
                .putExtra("liushui",s.getTransaction_number())
                .putExtra("type",s.getType_name())
                .putExtra("zhichu",s.getScore())
                .putExtra("time",s.getCreate_time())
                .putExtra("yue",s.getCurrent_score())
                );
            }
        });
    }
}