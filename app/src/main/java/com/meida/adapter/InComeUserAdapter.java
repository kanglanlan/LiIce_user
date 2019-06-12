package com.meida.adapter;

import android.content.Context;
import android.view.View;

import com.meida.MyView.CircleImageView;
import com.meida.liice.InCome;
import com.meida.liice.R;
import com.meida.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class InComeUserAdapter extends CommonAdapter<InCome.DataBean.DataBeans> {
    private ArrayList<InCome.DataBean.DataBeans> datas = new ArrayList<InCome.DataBean.DataBeans>();
    Context mContext;
    int type;

    public InComeUserAdapter(Context context, int layoutId, ArrayList<InCome.DataBean.DataBeans> datas, int type) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
        this.type = type;
    }

    @Override
    public void convert(final ViewHolder holder, final InCome.DataBean.DataBeans s, int position) {
        CircleImageView img=holder.getView(R.id.img_photo);
        if (type == 1) {
            holder.getView(R.id.ll_jifen).setVisibility(View.GONE);

        } else {
            holder.getView(R.id.ll_jifen).setVisibility(View.VISIBLE);
        }
        holder.setText(R.id.tv_jifen,s.getScore());
        holder.setText(R.id.tv_name,s.getNickname());
        holder.setText(R.id.tv_time,s.getCreate_time());
        holder.setText(R.id.tv_phone,s.getUser_tel());
        CommonUtil.setcimg(mContext,s.getLogo(),R.mipmap.ico_lb084,img);
    }
}