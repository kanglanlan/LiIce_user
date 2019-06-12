package com.meida.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.meida.liice.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * 作者 亢兰兰
 * 时间 2017/8/3 0003
 * 公司  郑州软盟
 */

public class PopupShareUtils {


    public static void showshare(final Activity baseContext, final Bitmap bitmap) {
        final View view = View.inflate(baseContext, R.layout.popu_share, null);
        Button bt = (Button) view.findViewById(R.id.bt_sharequxiao);
        TextView qq = (TextView) view.findViewById(R.id.tv_qq);
        TextView weixin = (TextView) view.findViewById(R.id.tv_weixin);
        TextView kongjian = (TextView) view.findViewById(R.id.tv_kongjan);
        TextView pengyou = (TextView) view.findViewById(R.id.tv_pengyouquan);
        final BottomBaseDialog sharedialog = new BottomBaseDialog(baseContext) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        sharedialog.show();
//
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedialog.dismiss();
                new ShareAction(baseContext)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedias(new UMImage(baseContext, bitmap))
                        .share();
            }
        });
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedialog.dismiss();

                UMImage imagelocal = new UMImage(baseContext, bitmap);
                imagelocal.setThumb(new UMImage(baseContext, bitmap));
                new ShareAction(baseContext).withMedia(imagelocal)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .share();
            }
        });
        kongjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedialog.dismiss();
                new ShareAction(baseContext)
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withMedias(new UMImage(baseContext, bitmap))
                        .share();
            }
        });
        pengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedialog.dismiss();
                new ShareAction(baseContext)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedias(new UMImage(baseContext, bitmap))
                        .share();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedialog.dismiss();
            }
        });
    }

}
