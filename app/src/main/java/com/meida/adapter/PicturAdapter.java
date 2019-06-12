package com.meida.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meida.liice.App;
import com.meida.liice.R;
import com.meida.utils.CommonUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.adapter.abslistview.CommonAdapter;

import java.io.File;
import java.util.ArrayList;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.meida.liice.YiJianFanKuiActivity.mSelectPathtwo;
import static com.meida.utils.CommonUtil.getPath;

/**
 * 作者 亢兰兰
 * 时间 2017/4/19 0019
 * 公司  郑州软盟
 */

public class PicturAdapter extends CommonAdapter<String> {
    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private Context context;

    public PicturAdapter(Context context, int layoutId, ArrayList<String> datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.mSelectPath = datas;
    }

    @Override
    protected void convert(com.zhy.adapter.abslistview.ViewHolder holder, final String item, int position) {
        ImageView iv = holder.getView(R.id.iv_pic_item_img);
        ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.width = (App.wid - CommonUtil.dip2px(context, 60)) /4;// 父布局 左右 10* 2个  gridview间距 5* 3个
        params.height = params.width ; //img上下 pading 为3   so  3* 2个
        iv.setLayoutParams(params);
        ImageView del = holder.getView(R.id.iv_pic_item_del);
        if (item.equals("")) {
            iv.setImageResource(R.drawable.pic_tu);
            del.setVisibility(View.GONE);
        } else {
            if (item.contains("http")) {
                ImageLoader.getInstance().displayImage(item, iv);
            } else {
                ImageLoader.getInstance().displayImage("file://" + item, iv);
            }
            del.setVisibility(View.VISIBLE);
        }
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mSelectPath.size(); i++) {
                    if (mSelectPath.get(i).equals("")) {
                        mSelectPath.remove(i);
                    }
                }
                for (int i = 0; i < mSelectPath.size(); i++) {
                    if (mSelectPath.get(i).equals(item)) {
                        mSelectPath.remove(i);
                    }
                }

                mSelectPathtwo.clear();
                Luban.with(mContext)
                        .load(mSelectPath)                                   // 传人要压缩的图片列表
                        .ignoreBy(100)                                  // 忽略不压缩图片的大小
                        .setTargetDir(getPath())                        // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                mSelectPathtwo.add(file.getAbsolutePath());
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();    //启动压缩
                mSelectPath.add("");
                notifyDataSetChanged();
            }
        });
    }
}