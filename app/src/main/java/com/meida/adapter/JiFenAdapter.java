package com.meida.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.meida.liice.App;
import com.meida.liice.JiFenDuiHuanActivity;
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

public class JiFenAdapter extends CommonAdapter<Goods.DataBean.DataBeans> {
    private ArrayList<Goods.DataBean.DataBeans> datas = new ArrayList<Goods.DataBean.DataBeans>();
    Context mContext;

    public JiFenAdapter(Context context, int layoutId, ArrayList<Goods.DataBean.DataBeans> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final Goods.DataBean.DataBeans s, int position) {
        ImageView imageView = holder.getView(R.id.img_jifen);
        int wid = (App.wid - (CommonUtil.sp2px(mContext, 40))) / 3;
        CommonUtil.setwidhe(imageView, wid, wid);
        holder.setText(R.id.tv_name, s.getTitle());
        holder.setText(R.id.tv_duihuan, "兑换积分：" + s.getSales_score_exchange());
        holder.setText(R.id.tv_butie, "补贴积分：" + s.getSales_score());
        CommonUtil.setimg(mContext, s.getLogo(), R.drawable.moren, imageView);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, JiFenDuiHuanActivity.class).putExtra("id", s.getId()));
            }
        });
    }
}