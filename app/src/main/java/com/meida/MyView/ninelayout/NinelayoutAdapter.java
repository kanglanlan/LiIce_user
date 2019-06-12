package com.meida.MyView.ninelayout;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.meida.liice.R;
import com.meida.utils.CommonUtil;

import java.util.List;

public class NinelayoutAdapter extends NineGridAdapter {
    private int type = 1;// 1原始 2 使用缩略图
    private Context context;

    public NinelayoutAdapter(Context context, List<MsgDetailImgLstBeanM> list, int type) {
        super(context, list);
        this.type = type;
        this.context = context;

    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    @Override
    public String getUrl(int position) {
//                return getItem(position) == null ? null : ((Image) getItem(position)).getUrl();
        return getItem(position) == null ? null : (String) getItem(position);
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setBackgroundColor(Color.parseColor("#ffffff"));
        if (type == 1) {
//            ImageLoader.getInstance().displayImage(((MsgDetailImgLstBeanM) getItem(i)).getOrginImg(), iv);//头像
            CommonUtil.setimg(context,((MsgDetailImgLstBeanM) getItem(i)).getOrginImg(), R.drawable.moren,iv);

        } else if (type == 2) {
//            ImageLoader.getInstance().displayImage(((MsgDetailImgLstBeanM) getItem(i)).getThumImg(), iv);//头像
            CommonUtil.setimg(context,((MsgDetailImgLstBeanM) getItem(i)).getThumImg(), R.drawable.moren,iv);
        }
        return iv;
    }

}