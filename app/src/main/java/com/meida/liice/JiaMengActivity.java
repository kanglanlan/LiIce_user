package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.gson.Gson;
import com.meida.model.CityList;
import com.meida.model.Coommt;
import com.meida.model.ServiceCity;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.GetJsonDataUtil;
import com.meida.utils.PreferencesUtils;
import com.meida.wheelview.OnWheelScrollListener;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;
import static com.meida.liice.R.id.img_shenqing_fws;

public class JiaMengActivity extends BaseActivity {

    @Bind(R.id.et_shenqing_weixinhao)
    EditText etShenqingWeixinhao;
    @Bind(R.id.et_shenqing_youxiang)
    EditText etShenqingYouxiang;
    @Bind(R.id.et_shenqing_name)
    EditText etShenqingName;
    @Bind(R.id.rb_nan)
    RadioButton rbNan;
    @Bind(R.id.rb_nv)
    RadioButton rbNv;
    @Bind(R.id.et_shenqing_nianling)
    EditText etShenqingNianling;
    @Bind(R.id.et_shenqing_shijian)
    EditText etShenqingShijian;
    @Bind(R.id.tv_shenqing_city)
    TextView tvShenqingCity;
    @Bind(R.id.tv_shenqing_fuwudi)
    TextView tv_shenqing_fuwudi;
    @Bind(R.id.img_shenqing_hexin)
    ImageView imgShenqingHexin;
    @Bind(img_shenqing_fws)
    ImageView imgShenqingFws;
    @Bind(R.id.ll_fws)
    LinearLayout llFws;
    private BottomBaseDialog adddialog;
    int xz = 2;//核心服务商   服务商

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_meng);
        ButterKnife.bind(this);
        changeTitle("填写申请表");
        rbNan.performClick();
//        getadd(1);
        cityadapter = new CommonAdapter<ServiceCity.DataBean.DatasBean>(baseContext,
                R.layout.item_city, servicedata) {
            @Override
            protected void convert(ViewHolder viewHolder, ServiceCity.DataBean.DatasBean item, int position) {
                viewHolder.setText(R.id.tv_city, item.getName());
            }
        };
    }

    @OnClick({R.id.tv_shenqing_city, R.id.ll_fuwu, R.id.tv_liaoje_hexin,
            R.id.ll_hxfws, R.id.ll_fws, R.id.tv_liaoje_fws, R.id.bt_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_hxfws:
//                xz = 1;
//                imgShenqingHexin.setImageResource(R.mipmap.ico_lb027);
//                imgShenqingFws.setImageResource(R.mipmap.ico_lb026);
                break;
            case R.id.ll_fws:
                xz = 2;
                imgShenqingHexin.setImageResource(R.mipmap.ico_lb026);
                imgShenqingFws.setImageResource(R.mipmap.ico_lb027);
                break;
            case R.id.tv_shenqing_city:
                initJsonData();
                break;
            case R.id.ll_fuwu:
                if (xz == 1) {
                    getsherng("1", "3", "");
                } else {
                    getsherng("1", "2", "");
                }
                break;
            case R.id.tv_liaoje_hexin:
                startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "109"));
                break;
            case R.id.tv_liaoje_fws:
                startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "110"));
                break;
            case R.id.bt_tijiao:
                if(TextUtils.isEmpty(etShenqingName.getText().toString()))
                {
                    showtoa("请输入姓名");
                    return;
                }
                if(TextUtils.isEmpty(etShenqingNianling.getText().toString()))
                {
                    showtoa("请输入年龄");
                    return;
                }
                if(TextUtils.isEmpty(tvShenqingCity.getText().toString()))
                {
                    showtoa("请选择所在城市");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.merchant_register, Const.POST);
                mRequest.add("user_tel", PreferencesUtils.getString(baseContext, "user_tel"));
                mRequest.add("weixin_number", etShenqingWeixinhao.getText().toString());
                mRequest.add("email", etShenqingYouxiang.getText().toString());
                mRequest.add("nickname", etShenqingName.getText().toString());
                if (rbNan.isChecked()) {
                    mRequest.add("sex", "1");
                } else {
                    mRequest.add("sex", "2");
                }
                mRequest.add("age", etShenqingNianling.getText().toString());
                mRequest.add("work_year_num", etShenqingShijian.getText().toString());
                    mRequest.add("merchant_type", "2");//服务商类型（1核心服务商;2服务商（合作伙伴））
                mRequest.add("province_id", province_id);
                mRequest.add("city_id", city_id);
                mRequest.add("area_id", area_id);
                mRequest.add("location_province_id", shengid);
                mRequest.add("location_city_id", shiid);
                mRequest.add("location_area_id", xianid);
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        if ("1".equals(data.getCode())) {
                            StartActivity(JiaMengokActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                CommonUtil.showToask(baseContext, obj.getString("msg"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
                break;
        }
    }


    CityList cityList;

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


    String province_id, city_id, area_id;
    CommonAdapter cityadapter;
    String province, city;
    TextView chengshi,quxian;
    TextView shengfens;
    private void showadd() {
        final View view = View.inflate(baseContext, R.layout.popu_add, null);
        shengfens = (TextView) view.findViewById(R.id.tv_shengfen);
         chengshi = (TextView) view.findViewById(R.id.tv_chengshi);
         quxian = (TextView) view.findViewById(R.id.tv_quxian);
        ListView list = (ListView) view.findViewById(R.id.listview);
        list.setAdapter(cityadapter);
        if ("2".equals(level_types)) {
            chengshi.setTextColor(getResources().getColor(R.color.main));
            shengfens.setTextColor(getResources().getColor(R.color.black));
        }
        if ("3".equals(level_types)) {
            quxian.setTextColor(getResources().getColor(R.color.main));
            shengfens.setTextColor(getResources().getColor(R.color.black));
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int po, long l) {

                if ("1".equals(level_types)) {
                    province = servicedata.get(po).getId();
                    if (xz == 1) {
                        getsherng("2", "3", servicedata.get(po).getId());
                    } else {
                        getsherng("2", "2", servicedata.get(po).getId());
                    }

                } else if ("2".equals(level_types)) {
                    city = servicedata.get(po).getId();
                    if (xz == 1) {
                        getsherng("3", "3", servicedata.get(po).getId());
                    } else {
                        getsherng("3", "2", servicedata.get(po).getId());
                    }
                } else {
                    province_id = province;
                    city_id = city;
                    area_id = servicedata.get(po).getId();
                    tv_shenqing_fuwudi.setText(servicedata.get(po).getMerger_name());
                    adddialog.dismiss();
                }
            }
        });
        adddialog = new BottomBaseDialog(baseContext) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        adddialog.show();
    }

    WheelView wv1, wv2, wv3;
    private String[] shengfen;
    private String[] quyu;
    private String[] xian;
    private BottomBaseDialog ldialog;

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
        title.setText("选择城市");
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
        ldialog = new BottomBaseDialog(this) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        ldialog.show();

    }

    String shengid, shiid, xianid;

    public void doclick(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                ldialog.dismiss();
                break;
            case R.id.tv_queding:
                ldialog.dismiss();
                shengid = cityList.getData().getList().get(wv1.getCurrentItem()).getId();
                shiid = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().get(wv2.getCurrentItem()).getId();
                xianid = cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getDistrict().get(wv3.getCurrentItem()).getId();
                tvShenqingCity.setText(cityList.getData().getList().get(wv1.getCurrentItem()).getName() + " "
                        + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getName() + " " + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getDistrict().get(wv3.getCurrentItem()).getName());
                break;
        }
    }


    /**
     * 获取生list
     */
    ArrayList<ServiceCity.DataBean.DatasBean> servicedata = new ArrayList<>();
    String level_types;

    public void getsherng(final String level_type, String type, String parent_id) {
        level_types = level_type;
        mRequest = NoHttp.createStringRequest(HttpIp.area_list, Const.POST);
        if (!TextUtils.isEmpty(parent_id)) {
            mRequest.add("parent_id", parent_id);
        }
        mRequest.add("level_type", level_type);//1省；2市；3县区
        mRequest.add("type", 2);//1全部2已开通（申请服务商）3申请核心服务商
        getRequest(new CustomHttpListener<ServiceCity>(baseContext, true, ServiceCity.class) {
            @Override
            public void doWork(ServiceCity data, String code) {
                if ("1".equals(data.getCode())) {
                    servicedata.clear();
                    servicedata.addAll(data.getData().getData());
//                    1省；2市；3县区
                    if (!"1".equals(level_type)) {
                        if ("2".equals(level_types)) {
                            chengshi.setTextColor(getResources().getColor(R.color.main));
                            shengfens.setTextColor(getResources().getColor(R.color.black));
                        }
                        if ("3".equals(level_types)) {
                            quxian.setTextColor(getResources().getColor(R.color.main));
                            shengfens.setTextColor(getResources().getColor(R.color.black));
                            chengshi.setTextColor(getResources().getColor(R.color.black));
                        }
                        cityadapter.notifyDataSetChanged();
                    } else {
                        showadd();
                    }
                }
            }
        }, true);
    }
}
