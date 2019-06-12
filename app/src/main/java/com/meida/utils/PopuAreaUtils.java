package com.meida.utils;

import android.content.Context;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.meida.liice.R;

/**
 * 作者 亢兰兰
 * 时间 2017/11/1 0001
 * 公司  郑州软盟
 */

public class PopuAreaUtils {


    public interface PopupWindowCallBack {
        void doWork(String id, String name);
    }


    public static void showDialog(
            final Context context,
            final String title,
            final String content,
            final String left,
            final String right,
            final HintCallBack msgCallBack) {
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.content(content)
                .title(title)
                .btnText(left, right)
                .btnTextColor(
                        context.getResources().getColor(R.color.black),
                        context.getResources().getColor(R.color.blue))
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {//left btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//right btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        msgCallBack.doWork();
                    }
                }
        );
    }

    public interface HintCallBack {
        void doWork();
    }

}
