package com.meida.adapter;

import android.content.Context;

import com.meida.liice.R;
import com.meida.model.JiFenList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class IntegerlAdapter extends CommonAdapter<JiFenList.DataBean.DataBsean> {
    private ArrayList<JiFenList.DataBean.DataBsean> datas = new ArrayList<JiFenList.DataBean.DataBsean>();
    Context mContext;
    String tag;

    public IntegerlAdapter(Context context, String tag, int layoutId, ArrayList<JiFenList.DataBean.DataBsean> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        this.tag = tag;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final JiFenList.DataBean.DataBsean data, int position) {
        holder.setText(R.id.tv_title, data.getType_name())
                .setText(R.id.tv_bianhao, "交易编号：" + data.getTransaction_number())
                .setText(R.id.tv_time, "交易时间：" + data.getCreate_time());
        if ("3".equals(tag)) {
            holder.setText(R.id.tv_title,"转赠");
        }else   if ("2".equals(tag)) {
            holder.setText(R.id.tv_title,"提现");
        }
        if ("1".equals(tag)) {
            holder.setText(R.id.tv_money, "+" + data.getScore());
        } else {
            holder.setText(R.id.tv_money, "-" + data.getScore());
        }
    }
}