package com.meida.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.gson.Gson;
import com.meida.liice.BaoBeiOkActivity;
import com.meida.liice.R;
import com.meida.model.CityList;
import com.meida.model.Coommt;
import com.meida.model.FenLeiList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.GetJsonDataUtil;
import com.meida.utils.Nonce;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class fragment2 extends BaseFragment {
    String[] datas;
    @Bind(R.id.rb_zulin)
    RadioButton rbZulin;
    @Bind(R.id.rb_gouxiao)
    RadioButton rbGouxiao;
    @Bind(R.id.tv_pinpai)
    TextView tvPinpai;
    @Bind(R.id.tv_xiangmuleixing)
    TextView tvXiangmuleixing;
    @Bind(R.id.tv_kongtialeixing)
    TextView tvKongtialeixing;
    @Bind(R.id.et_yezhuname)
    EditText etYezhuname;
    @Bind(R.id.et_yezhudianhua)
    EditText etYezhudianhua;
    @Bind(R.id.et_xiangmuname)
    EditText etXiangmuname;
    @Bind(R.id.et_mianji)
    EditText etMianji;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.et_xxadd)
    EditText etXxadd;
    @Bind(R.id.et_miaoshu)
    EditText etMiaoshu;
    private BottomBaseDialog dialog;
    private BottomBaseDialog adddialog;
    int type = 2;
    private final int DECIMAL_DIGITS = 2;//小数的位数

    public static fragment2 instantiation() {
        fragment2 fragment = new fragment2();
        return fragment;
    }

    //调用这个方法切换时不会释放掉Fragment
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getadd(1);
        rbGouxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPinpai.setText("");
            }
        });
        rbZulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPinpai.setText("立冰");
            }
        });
        etMianji.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etMianji.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                        etMianji.setText(s);
                        etMianji.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etMianji.setText(s);
                    etMianji.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etMianji.setText(s.subSequence(0, 1));
                        etMianji.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void showpopu(final int type, final String[] string) {
        final View view = View.inflate(getActivity(), R.layout.popu_one, null);
        final WheelView wv1 = (WheelView) view.findViewById(R.id.wl_1);
        TextView title = (TextView) view.findViewById(R.id.tv_popu_title);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        TextView tv_queding = (TextView) view.findViewById(R.id.tv_queding);
        switch (type) {
            case 1:
                title.setText("选择品牌");
                break;
            case 2:
                title.setText("选择项目类型");
                break;
            case 3:
                title.setText("选择意向空调类型");
                break;
        }
        wv1.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), string));
        dialog = new BottomBaseDialog(getActivity()) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        dialog.show();
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if ("其它".equals(string[wv1.getCurrentItem()])) {
                    showedit(type, "请填写项目类型");
                } else {
                    switch (type) {
                        case 1:
                            tvPinpai.setText(string[wv1.getCurrentItem()]);
                            break;
                        case 2:
                            tvXiangmuleixing.setText(string[wv1.getCurrentItem()]);
                            break;
                        case 3:
                            tvKongtialeixing.setText(string[wv1.getCurrentItem()]);
                            break;
                    }
                }

            }
        });
    }

    private void showedit(final int i, String title) {
        final EditText inputServer = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setView(inputServer)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (i) {
                    case 1:
                        tvPinpai.setText(inputServer.getText().toString());
                        break;
                    case 2:
                        tvXiangmuleixing.setText(inputServer.getText().toString());
                        break;
                    case 3:
                        tvKongtialeixing.setText(inputServer.getText().toString());
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * 示例 1商品分类2 对应品牌  3 对应项目类型 4对应意向空调类型 6 银行卡 101商品标签 102商品规格 1001项目规模）
     */
    public void getdatas(final int typea) {
        Nonce.getpinpai(true, typea, getActivity(), new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                datas = new String[data.getData().getList().size()];
                for (int i = 0; i < data.getData().getList().size(); i++) {
                    datas[i] = data.getData().getList().get(i).getTitle();
                }
                showpopu(type, datas);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.ll_pinpai, R.id.ll_xiangmuleixing, R.id.ll_kongtiaoleixing, R.id.ll_add, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pinpai:
                if (!rbZulin.isChecked()) {
                    type = 1;
                    getdatas(2);
                }
                break;
            case R.id.ll_xiangmuleixing:
                type = 2;
                getdatas(3);
                break;
            case R.id.ll_kongtiaoleixing:
                type = 3;
                getdatas(4);
                break;
            case R.id.ll_add:
//                if (cityList == null) {
//                    getadd(2);
//                } else {
//                    showadd(2);
//                }
                initJsonData();
                break;
            case R.id.bt_login:
//                if (TextUtils.isEmpty(tvPinpai.getText().toString())) {
//                    CommonUtil.showToask(getActivity(), "请选择空调类型");
//                    return;
//                }
                if (TextUtils.isEmpty(etYezhuname.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请填写负责人姓名");
                    return;
                }
                if (TextUtils.isEmpty(etYezhudianhua.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请填写负责人电话");
                    return;
                }
                if (TextUtils.isEmpty(etXiangmuname.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请填写项目名称");
                    return;
                }
                if (TextUtils.isEmpty(etMianji.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请填写项目面积");
                    return;
                }
                if (TextUtils.isEmpty(tvAdd.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请选择项目地址");
                    return;
                }
                if (TextUtils.isEmpty(etXxadd.getText().toString())) {
                    CommonUtil.showToask(getActivity(), "请输入项目详细地址");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.project, Const.POST);
                mRequest.add("brand_name", tvPinpai.getText().toString());
                mRequest.add("property_name", tvXiangmuleixing.getText().toString());
                mRequest.add("product_type_name", tvKongtialeixing.getText().toString());
                mRequest.add("address_name", etYezhuname.getText().toString());
                mRequest.add("address_tel", etYezhudianhua.getText().toString());
                mRequest.add("title", etXiangmuname.getText().toString());
                mRequest.add("area", etMianji.getText().toString());
                mRequest.add("address", etXxadd.getText().toString());
                mRequest.add("content", etMiaoshu.getText().toString());
                mRequest.add("province_id", shengid);
                mRequest.add("city_id", shiid);
                mRequest.add("area_id", xianid);
                if (rbZulin.isChecked()) {//成交方式不能为空 1租/分期；2买/全款
                    mRequest.add("pay_type", 1);
                } else {
                    mRequest.add("pay_type", 2);
                }
                getRequest(new CustomHttpListener<Coommt>(getActivity(), true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        if ("1".equals(data.getCode())) {
                            tvPinpai.setText("");
                            tvKongtialeixing.setText("");
                            tvXiangmuleixing.setText("");
                            etXxadd.setText("");
                            etYezhudianhua.setText("");
                            etYezhuname.setText("");
                            etMiaoshu.setText("");
                            etXiangmuname.setText("");
                            tvAdd.setText("");
                            etMianji.setText("");
                            startActivity(new Intent(getActivity(), BaoBeiOkActivity.class)
                                    .putExtra("isshow", "2")
                                    .putExtra("tag", "1")
                            );
                        }
                    }

                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                CommonUtil.showToask(getActivity(), obj.getString("msg"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);

                break;
        }
    }


    /**
     * 项目地址
     */
    ArrayList<CityList.DataBean.ListBean.CityListBean.DistrictBean> citydata = new ArrayList<>();
    int select = 1;//省市区当前选项
    int shengps, ships, xianps;
    String shengid, shiid, xianid;
    CommonAdapter cityadapter;

    private void showadd() {
        select = 1;
        citydata.clear();
        final View view = View.inflate(getActivity(), R.layout.popu_add, null);
        final TextView shengfen = (TextView) view.findViewById(R.id.tv_shengfen);
        final TextView chengshi = (TextView) view.findViewById(R.id.tv_chengshi);
        final TextView quxian = (TextView) view.findViewById(R.id.tv_quxian);
        ListView list = (ListView) view.findViewById(R.id.listview);
        for (int i = 0; i < cityList.getData().getList().size(); i++) {
            CityList.DataBean.ListBean.CityListBean.DistrictBean bean = new CityList.DataBean.ListBean.CityListBean.DistrictBean();
            bean.setName(cityList.getData().getList().get(i).getName());
            citydata.add(bean);
        }
        cityadapter = new CommonAdapter<CityList.DataBean.ListBean.CityListBean.DistrictBean>(getActivity(),
                R.layout.item_city, citydata) {
            @Override
            protected void convert(ViewHolder viewHolder, CityList.DataBean.ListBean.CityListBean.DistrictBean item, int position) {
                viewHolder.setText(R.id.tv_city, item.getName());
            }
        };
        list.setAdapter(cityadapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int po, long l) {
                if (select == 1) {
                    chengshi.setTextColor(getActivity().getResources().getColor(R.color.main));
                    shengfen.setTextColor(getActivity().getResources().getColor(R.color.black));
                    quxian.setTextColor(getActivity().getResources().getColor(R.color.black));
                    select = 2;
                    shengps = po;
                    citydata.clear();
                    for (int i = 0; i < cityList.getData().getList().get(po).getCityList().size(); i++) {
                        CityList.DataBean.ListBean.CityListBean.DistrictBean bean = new CityList.DataBean.ListBean.CityListBean.DistrictBean();
                        bean.setName(cityList.getData().getList().get(po).getCityList().get(i).getName());
                        citydata.add(bean);
                    }
                    cityadapter.notifyDataSetChanged();
                } else if (select == 2) {
                    select = 3;
                    chengshi.setTextColor(getActivity().getResources().getColor(R.color.black));
                    shengfen.setTextColor(getActivity().getResources().getColor(R.color.black));
                    quxian.setTextColor(getActivity().getResources().getColor(R.color.main));
                    ships = po;
                    citydata.clear();
                    for (int i = 0; i < cityList.getData().getList().get(shengps).getCityList().get(po).getDistrict().size(); i++) {
                        CityList.DataBean.ListBean.CityListBean.DistrictBean bean = new CityList.DataBean.ListBean.CityListBean.DistrictBean();
                        bean.setName(cityList.getData().getList().get(shengps).getCityList().get(po).getDistrict().get(i).getName());
                        citydata.add(bean);
                    }
                    cityadapter.notifyDataSetChanged();
                } else {
                    select = 1;
                    xianps = po;
                    shengid = cityList.getData().getList().get(shengps).getId();
                    shiid = cityList.getData().getList().get(shengps).getCityList().get(ships).getId();
                    xianid = cityList.getData().getList().get(shengps).getCityList().get(ships).getDistrict().get(po).getId();
                    tvAdd.setText(cityList.getData().getList().get(shengps).getName() +
                            cityList.getData().getList().get(shengps).getCityList().get(ships).getName() +
                            cityList.getData().getList().get(shengps).getCityList().get(ships).getDistrict().get(po).getName());
                    adddialog.dismiss();
                }
            }
        });
        adddialog = new BottomBaseDialog(getActivity()) {
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

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(getActivity(), "province.json");//获取assets目录下的json文件数据
        Gson gson = new Gson();
        cityList=gson.fromJson(JsonData,CityList.class);
        showadd();
    }

    CityList cityList;
//    public void getadd(final int type) {
//        Nonce.getadd(getActivity(), new Nonce.CityCallback() {
//            @Override
//            public void doWork(CityList data) {
//                cityList = data;
//                showadd(type);
//            }
//        });
//    }
}
