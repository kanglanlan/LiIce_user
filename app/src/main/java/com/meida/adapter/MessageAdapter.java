package com.meida.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.meida.liice.R;
import com.meida.liice.WebViewActivity;
import com.meida.model.Goods;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class MessageAdapter extends CommonAdapter<Goods.DataBean.DataBeans> {
    private ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<Goods.DataBean.DataBeans>();
    Context mContext;

    public MessageAdapter(Context context, int layoutId, ArrayList<Goods.DataBean.DataBeans> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final Goods.DataBean.DataBeans s, int position) {
        holder.setText(R.id.tv_title, s.getTitle());
        holder.setText(R.id.tv_content, s.getContent());
        holder.setText(R.id.tv_time, s.getCreate_time());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, WebViewActivity.class).putExtra("id", s.getId()));
            }
        });
    }
}