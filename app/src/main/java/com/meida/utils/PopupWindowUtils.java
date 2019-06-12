package com.meida.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.meida.adapter.TYpeAdapter;
import com.meida.liice.R;
import com.meida.model.FenLeiList;

import java.util.ArrayList;

/**
 * 作者 亢兰兰
 * 时间 2017/8/3 0003
 * 公司  郑州软盟
 */

public class PopupWindowUtils {

    /**
     * @param context
     * @param anchor   线，要显示的那个view下面
     * @param data     数据源
     * @param type     1单选   2多选
     * @param callBack 点击回调监听
     */
    public static void populist(Context context, View anchor, final ArrayList<FenLeiList.DataBean.ListBean> data, final int type, final PopupWindowCallBack callBack) {
        View view = LayoutInflater.from(context).inflate(R.layout.poputype_one, null);
        final PopupWindow popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE); // 适配华为p8
        View divider = view.findViewById(R.id.v_pop_divider);
        ListView listview = (ListView) view.findViewById(R.id.list_popu);

        View divider_top = view.findViewById(R.id.v_pop_divider_top);
        divider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击下面的透明部分去取消弹窗
                popupWindow.dismiss();
            }
        });

        //放数据
        final TYpeAdapter adapter = new TYpeAdapter(context, R.layout.item_textviewtype, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < data.size(); j++) {
                    data.get(j).setCheck(0);
                }
                popupWindow.dismiss();
                data.get(i).setCheck(1);
                adapter.notifyDataSetChanged();
                callBack.doWork(data.get(i).getId());
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_anim_style);
        //必须要有这句否则弹出popupWindow后监听不到Back键
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        //使其聚集
        popupWindow.setFocusable(true);
        //设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            divider_top.setVisibility(View.GONE);
            popupWindow.showAsDropDown(anchor);
        } else {
            divider_top.setVisibility(View.VISIBLE);
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            showAsDropDowns(popupWindow,anchor,  0, 0);
//            popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, location[0], location[1]);
        }
        //刷新状态（必须刷新否则无效）
        popupWindow.update();
    }

    /**
     * @param pw     popupWindow
     * @param anchor v
     * @param xoff   x轴偏移
     * @param yoff   y轴偏移
     */
    public static void showAsDropDowns(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
//            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }
    public interface PopupWindowCallBack {
        void doWork(String id);
    }


}
