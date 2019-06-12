package com.meida.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.flyco.dialog.widget.base.BaseDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.meida.liice.BuTieInfoActivity;
import com.meida.liice.R;
import com.meida.liice.ZuLinActivity;
import com.meida.model.LunBoTu;

/**
 * 作者 亢兰兰
 * 时间 2018/9/5 0005
 * 公司  郑州软盟
 */

public class AdDialog extends BaseDialog<AdDialog> {
    private Context context;
    private RoundedImageView iv_ad;
    private ImageView back;
    LunBoTu.DataBean.ListBean listBean;

    public AdDialog(Context context, LunBoTu.DataBean.ListBean listBean) {
        super(context);
        this.context = context;
        this.listBean = listBean;
    }

    //该方法用来出来数据初始化代码
    @Override
    public View onCreateView() {
        widthScale(0.85f);
        //填充弹窗布局
        View inflate = View.inflate(context, R.layout.dia_popu, null);
        //用来放整个图片的控件
        iv_ad = (RoundedImageView) inflate.findViewById(R.id.iv_ad);
        //放在透明部分和错号上的隐形控件，用来点击使弹窗消失
        back = (ImageView) inflate.findViewById(R.id.ad_back);
        //用来加载网络图片，填充iv_ad控件，注意要添加网络权限，和Picasso的依赖和混淆
        CommonUtil.setimg(context, listBean.getLogo(), R.drawable.moren, iv_ad);
        return inflate;
    }

    //该方法用来处理逻辑代码
    @Override
    public void setUiBeforShow() {
        //点击弹窗相应位置，处理相关逻辑。
        iv_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1无跳转；2外部url；3自定义图文；4商品
                switch (listBean.getRedirect_type()) {
                    case "2":
                        context.startActivity(new Intent(context, ZuLinActivity.class)
                                .putExtra("tag", "2")
                                .putExtra("info",  listBean.getRedirect_value())
                        );
                        break;
                    case "3":
                        context.startActivity(new Intent(context, ZuLinActivity.class)
                                .putExtra("tag", "1")
                                .putExtra("info",  listBean.getDetail())
                        );
                        break;
                    case "4":
                        context.startActivity(new Intent(context, BuTieInfoActivity.class)
                                .putExtra("id", listBean.getRedirect_value()));
                        break;
                }
                //处理完逻辑关闭弹框的代码
                dismiss();
            }
        });
        //点×关闭弹框的代码
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭弹框的代码
                dismiss();
            }
        });
    }
}