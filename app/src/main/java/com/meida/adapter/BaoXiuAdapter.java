package com.meida.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.meida.liice.AddBaoXiuActivity;
import com.meida.liice.R;
import com.meida.model.BaoXiuList;
import com.meida.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class BaoXiuAdapter extends CommonAdapter<BaoXiuList.DataBean.DatsaBean> {
    private ArrayList<BaoXiuList.DataBean.DatsaBean> datas = new ArrayList<BaoXiuList.DataBean.DatsaBean>();
    Context mContext;

    public BaoXiuAdapter(Context context, int layoutId, ArrayList<BaoXiuList.DataBean.DatsaBean> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final BaoXiuList.DataBean.DatsaBean s, int position) {
        ImageView img = holder.getView(R.id.img_goumai_photo);
        CommonUtil.setimg(mContext, s.getProduct_logo(), R.drawable.moren, img);
        holder.setText(R.id.tv_name, s.getProduct_name());
        holder.setText(R.id.tv_content, s.getProduct_spec());
        holder.setText(R.id.tv_money, s.getProduct_price());
        holder.setText(R.id.tv_bianhao, "订单编号：" + s.getOrder_id());
        holder.setText(R.id.tv_goumai, s.getProduct_num() + "人购买");
        holder.getView(R.id.tv_goumai).setVisibility(View.GONE);
        holder.getView(R.id.baoxiu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AddBaoXiuActivity.class)
                        .putExtra("order_id", s.getOrder_id())
                        .putExtra("product_id", s.getProduct_id())
                );
            }
        });
    }
}