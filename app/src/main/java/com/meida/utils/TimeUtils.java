package com.meida.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.meida.liice.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andriod0729 on 2017/10/19.
 */

public class TimeUtils {
    private static TimeUtils instance;

    private TimeUtils() {
    }

    public static synchronized TimeUtils getInstance() {
        if (instance == null) {
            instance = new TimeUtils();
        }
        return instance;
    }

    private TimePickerView pvTime;

    public void initTimePicker(final Activity context, final TextView tvBirth) {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DATE), selectedDate.get(Calendar.HOUR_OF_DAY), selectedDate.get(Calendar.MINUTE));
        Calendar endDate = Calendar.getInstance();
        endDate.set(selectedDate.get(Calendar.YEAR)+1, selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(final Date date, View v) {//选中事件回调
                tvBirth.setText(getTime2(date));
            }
        })
                .setLabel("年", "月", "日", "时", "分", "") //设置空字符串以隐藏单位提示   hide label
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time2, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.DKGRAY)
                .build();


        if (pvTime != null) {
            pvTime.show();
        }
    }

    private String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }
}
