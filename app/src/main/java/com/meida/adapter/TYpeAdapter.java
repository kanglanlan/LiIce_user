package com.meida.adapter;

import android.content.Context;
import android.widget.TextView;

import com.meida.liice.R;
import com.meida.model.FenLeiList;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class TYpeAdapter extends CommonAdapter<FenLeiList.DataBean.ListBean> {
    private ArrayList<FenLeiList.DataBean.ListBean> datas = new ArrayList<FenLeiList.DataBean.ListBean>();
    Context mContext;

    public TYpeAdapter(Context context, int layoutId, ArrayList<FenLeiList.DataBean.ListBean> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final FenLeiList.DataBean.ListBean s, int position) {
        TextView tv = holder.getView(R.id.tv_type);
        tv.setText(s.getTitle());
        if (s.getCheck() == 1) {
            tv.setTextColor(mContext.getResources().getColor(R.color.main));
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.black));
        }

    }
}