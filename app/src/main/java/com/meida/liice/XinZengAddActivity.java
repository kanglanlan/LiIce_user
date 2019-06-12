package com.meida.liice;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.gson.Gson;
import com.meida.model.CityList;
import com.meida.model.Coommt;
import com.meida.model.Goods;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.GetJsonDataUtil;
import com.meida.wheelview.OnWheelScrollListener;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;

public class XinZengAddActivity extends BaseActivity {

    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_shengshiqu)
    TextView tvShengshiqu;
    @Bind(R.id.et_jiedaodizhi)
    EditText etJiedaodizhi;
    @Bind(R.id.cb_morendizhi)
    CheckBox cbMorendizhi;
    @Bind(R.id.tv_shanchu)
    TextView tvShanchu;
    private String[] shengfen;
    private String[] quyu;
    private String[] xian;
    private BottomBaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_zeng_add);
        ButterKnife.bind(this);
        switch (getIntent().getStringExtra("tag")) {
            case "1":
                changeTitle("新建收货地址");
                break;
            case "2":
                changeTitle("编辑地址");
                etName.setText(getIntent().getStringExtra("name"));
                etPhone.setText(getIntent().getStringExtra("phone"));
                etJiedaodizhi.setText(getIntent().getStringExtra("xxadd"));
                tvShengshiqu.setText(getIntent().getStringExtra("add"));
                shengid = getIntent().getStringExtra("sid");
                shiid = getIntent().getStringExtra("shiid");
                xianid = getIntent().getStringExtra("xianid");
                tvShanchu.setVisibility(View.VISIBLE);
//                是否为默认地址1默认 2不默认
                if ("1".equals(getIntent().getStringExtra("is"))) {
                    cbMorendizhi.performClick();
                }
                break;
        }
        tvTitleRight.setText("完成");
//        getadd(1);
    }

    @OnClick({R.id.tv_title_right, R.id.tv_shengshiqu, R.id.tv_shanchu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    showtoa("请输入姓名");
                    return;
                }
                if (etPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(tvShengshiqu.getText().toString())) {
                    showtoa("请选择地址");
                    return;
                }
                if (TextUtils.isEmpty(etJiedaodizhi.getText().toString())) {
                    showtoa("请填写街道地址");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.address, Const.POST);
                if (!TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
                    mRequest.add("id", getIntent().getStringExtra("id"));
                }
                mRequest.add("address_name", etName.getText().toString());
                mRequest.add("address_tel", etPhone.getText().toString());
                mRequest.add("province_id", shengid);
                mRequest.add("city_id", shiid);
                mRequest.add("area_id", xianid);
                mRequest.add("address", etJiedaodizhi.getText().toString());
                if (cbMorendizhi.isChecked())//是否为默认地址1默认 2不默认
                {
                    mRequest.add("is_default", 1);
                } else {
                    mRequest.add("is_default", 2);
                }
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        if ("1".equals(data.getCode())) {
                            finish();
                        }
                    }
                }, true);
                break;
            case R.id.tv_shengshiqu:
                initJsonData();
                break;
            case R.id.tv_shanchu:
                final NormalDialog dialog = new NormalDialog(baseContext);
                dialog.content("是否确定删除改地址？")
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideBottomExit())
                        .show();
                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                Log.i("=======", "点击取消");
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {

                                dialog.dismiss();
                                mRequest = NoHttp.createStringRequest(HttpIp.delAddress, Const.POST);
                                mRequest.add("ids", getIntent().getStringExtra("id"));
                                getRequest(new CustomHttpListener<Goods>(baseContext, true, Goods.class) {
                                    @Override
                                    public void doWork(Goods data, String code) {
                                        if ("1".equals(data.getCode())) {
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                                        super.onFinally(obj, code, isNetSucceed);
                                        if (obj != null) {
                                            try {
                                                showtoa(obj.getString("msg"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }, true);
                            }
                        });


                break;
        }
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        Gson gson = new Gson();
        cityList=gson.fromJson(JsonData,CityList.class);
        shengfen = new String[cityList.getData().getList().size()];
        for (int i = 0; i < cityList.getData().getList().size(); i++) {
            shengfen[i] = cityList.getData().getList().get(i).getName();
        }
        quyu = new String[cityList.getData().getList().get(0).getCityList().size()];
        for (int i = 0; i < cityList.getData().getList().get(0).getCityList().size(); i++) {
            quyu[i] = cityList.getData().getList().get(0).getCityList().get(i).getName();
        }
        xian = new String[cityList.getData().getList().get(0).getCityList().get(0).getDistrict().size()];
        for (int i = 0; i < cityList.getData().getList().get(0).getCityList().get(0).getDistrict().size(); i++) {
            xian[i] = cityList.getData().getList().get(0).getCityList().get(0).getDistrict().get(i).getName();
        }
        showpopu(type);
    }

    CityList cityList;


    WheelView wv1, wv2, wv3;

    private void showpopu(int type) {
        if (type == 1) {
            return;
        }
        final View view = View.inflate(this, R.layout.popu_lunbo, null);
        wv1 = (WheelView) view.findViewById(R.id.wl_1);
        wv2 = (WheelView) view.findViewById(R.id.wl_2);
        wv3 = (WheelView) view.findViewById(R.id.wl_3);
        wv3.setVisibility(View.VISIBLE);
        TextView title = (TextView) view.findViewById(R.id.tv_popu_title);
        title.setText("选择地址");
        wv1.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, shengfen));
        wv2.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, quyu));
        wv3.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, xian));
        wv1.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                try {
                    quyu = new String[cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().size()];
                    for (int i = 0; i < cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().size(); i++) {
                        quyu[i] = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(i).getName();
                    }
                    wv2.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, quyu));
                    wv2.setCurrentItem(0);
                    xian = new String[cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().size()];
                    for (int i = 0; i < cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().size(); i++) {
                        xian[i] = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().get(i).getName();
                    }
                    wv3.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, xian));
                    wv3.setCurrentItem(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        wv2.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                xian = new String[cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().size()];
                for (int i = 0; i < cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().size(); i++) {
                    xian[i] = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getDistrict().get(i).getName();
                }
                wv3.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, xian));
                wv3.setCurrentItem(0);
            }
        });
        dialog = new BottomBaseDialog(this) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        dialog.show();

    }

    String shengid, shiid, xianid;

    public void doclick(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                dialog.dismiss();
                break;
            case R.id.tv_queding:
                dialog.dismiss();
                shengid = cityList.getData().getList().get(wv1.getCurrentItem()).getId();
                shiid = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getId();
                xianid = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getDistrict().get(wv3.getCurrentItem()).getId();
                tvShengshiqu.setText(cityList.getData().getList().get(wv1.getCurrentItem()).getName() + " "
                        + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getName() + " " + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getDistrict().get(wv3.getCurrentItem()).getName());
                break;
        }
    }
}
