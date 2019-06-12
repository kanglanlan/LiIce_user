package com.meida.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.meida.MyView.CircleImageView;
import com.meida.MyView.CustomGridView;
import com.meida.liice.App;
import com.meida.liice.R;
import com.meida.model.PingJiaList;
import com.meida.photoview.demo.ImagePagerActivity;
import com.meida.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import static com.meida.liice.R.id.img;


/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class PingJiaAdapter extends CommonAdapter<PingJiaList.DataBean.DataBeans> {
    private ArrayList<PingJiaList.DataBean.DataBeans> datas = new ArrayList<PingJiaList.DataBean.DataBeans>();
    Context mContext;

    public PingJiaAdapter(Context context, int layoutId, ArrayList<PingJiaList.DataBean.DataBeans> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final PingJiaList.DataBean.DataBeans s, int position) {
        CircleImageView img_p = holder.getView(R.id.img_p);
        CommonUtil.setcimg(mContext, s.getLogo(), R.drawable.moren, img_p);
        holder.setText(R.id.tv_name, s.getNickname());
        holder.setText(R.id.tv_content, s.getContent());
        holder.setText(R.id.tv_time, s.getCreate_time());
        CustomGridView gridView = holder.getView(R.id.gv_pic);
        gridView.setAdapter(new com.zhy.adapter.abslistview.CommonAdapter<PingJiaList.DataBean.DataBeans.SmetaBean>
                (mContext, R.layout.item_gvimg, s.getSmeta()) {
            @Override
            protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, PingJiaList.DataBean.DataBeans.SmetaBean item, int position) {
                ImageView imageView = viewHolder.getView(img);
                int wid = (App.wid - (CommonUtil.sp2px(mContext, 40))) / 3;
                CommonUtil.setwidhe(imageView, wid, wid);
                CommonUtil.setimg(mContext, item.getUrl(), R.drawable.moren, imageView);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int po, long l) {
                String[] aStrings = new String[s.getSmeta().size()];
                for (int i = 0; i < s.getSmeta().size(); i++) {

                    aStrings[i] = s.getSmeta().get(i).getUrl();
                }
                Intent intent = new Intent(mContext,
                        ImagePagerActivity.class);
                // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                        aStrings);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,
                        po);
                mContext.startActivity(intent);
            }
        });

    }
}