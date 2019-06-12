package com.meida.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.liice.BuTieInfoActivity;
import com.meida.liice.R;
import com.meida.model.Goods;
import com.meida.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class BuTieAdapter extends CommonAdapter<Goods.DataBean.DataBeans> {
    private ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<Goods.DataBean.DataBeans>();
    Context mContext;

    public BuTieAdapter(Context context, int layoutId, ArrayList<Goods.DataBean.DataBeans> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final Goods.DataBean.DataBeans s, int position) {
        ImageView imageView = holder.getView(R.id.img_goumai_photo);
        CommonUtil.setimg(mContext, s.getLogo(), R.drawable.moren, imageView);
        holder.setText(R.id.tv_name, s.getTitle());
        holder.setText(R.id.tv_content, s.getContent());
        holder.setText(R.id.tv_content, s.getContent());
        holder.setText(R.id.tv_money, s.getUser_price());
        holder.setText(R.id.tv_goumai, s.getUser_sales_num() + "人购买");
        if ("0".equals(s.getUser_score()) || "0.00".equals(s.getUser_score())) {
            holder.setText(R.id.tv_butie, "享" + s.getUser_score_exchange() + "商品兑换积分");
        } else if ("0".equals(s.getUser_score_exchange()) || "0.00".equals(s.getUser_score_exchange())) {
            holder.setText(R.id.tv_butie, "享" + s.getUser_score() + "元电费补贴");
        } else {
            holder.setText(R.id.tv_butie, "享" + s.getUser_score() + "元电费补贴 + "
                    + s.getUser_score_exchange() + "商品兑换积分");
        }
        LinearLayout ll_bq = holder.getView(R.id.ll_bq);
        ll_bq.removeAllViews();
        if (s.getLabels().size() > 0) {
            for (int i = 0; i < s.getLabels().size(); i++) {
                View view = View.inflate(mContext, R.layout.item_biaoqian, null);
                TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                textView.setText(s.getLabels().get(i));
                ll_bq.addView(view);
            }
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, BuTieInfoActivity.class).putExtra("id", s.getId()));
            }
        });
    }
}