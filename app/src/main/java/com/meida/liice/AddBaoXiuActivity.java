package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.gson.Gson;
import com.meida.MyView.CustomGridView;
import com.meida.adapter.PicturAdapter;
import com.meida.model.CityList;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.GetJsonDataUtil;
import com.meida.utils.TimeUtils;
import com.meida.wheelview.OnWheelScrollListener;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.R.attr.type;
import static com.meida.liice.YiJianFanKuiActivity.mSelectPathtwo;
import static com.meida.utils.CommonUtil.getPath;

public class AddBaoXiuActivity extends BaseActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.et_xxadd)
    EditText etXxadd;
    @Bind(R.id.et_miaoshu)
    EditText etMiaoshu;
    @Bind(R.id.gv_pic)
    CustomGridView gvYj;
    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private PicturAdapter adapter;
    private BottomBaseDialog dialog;
    private String[] shengfen;
    private String[] quyu;
    private String[] xian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bao_xiu);
        ButterKnife.bind(this);
        changeTitle("填写报修信息");
        mSelectPathtwo = new ArrayList<String>();
        init();
//        getadd(1);
    }

    private void init() {
        mSelectPath.add("");
        adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
        gvYj.setAdapter(adapter);
        gvYj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //创建PermissionUtil对象，参数为继承自V4包的 FragmentActivity
                if (mSelectPath.get(position).equals("")) {
                    int selectedMode = 1;
                    Intent intent = new Intent(baseContext,
                            MultiImageSelectorActivity.class);
                    // 是否显示拍摄图片
                    intent.putExtra("show_camera", true);
                    // 最大可选择图片数量
                    intent.putExtra("max_select_count", 9);
                    // 选择模式
                    intent.putExtra("select_count_mode", selectedMode);
                    // 默认选择
                    if (mSelectPath != null && mSelectPath.size() > 0) {
                        intent.putExtra("default_list", mSelectPath);
                    }
                    mSelectPath.remove(mSelectPath.size() - 1);
                    startActivityForResult(intent, 2);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (int i = 0; i < mSelectPath.size(); i++) {
            if (TextUtils.isEmpty(mSelectPath.get(i))) {
                mSelectPath.remove(i);
            }
        }
        if (resultCode == RESULT_OK) {
            mSelectPath = data
                    .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mSelectPathtwo.clear();
            Luban.with(this)
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
            if (mSelectPath.size() < 9) {
                mSelectPath.add("");
            }
            adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
            gvYj.setAdapter(adapter);
        } else {
            if (mSelectPath.size() < 9) {
                mSelectPath.add("");
            }
            adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
            gvYj.setAdapter(adapter);
        }


    }

    @OnClick({R.id.ll_time, R.id.ll_add, R.id.bt_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
                TimeUtils.getInstance().initTimePicker(baseContext, tvTime);
                break;
            case R.id.ll_add:
                initJsonData();
                break;
            case R.id.bt_tijiao:
                if (TextUtils.isEmpty(etName.getText())) {
                    showtoa("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(etPhone.getText())) {
                    showtoa("请输入联系电话");
                    return;
                }
                if (TextUtils.isEmpty(tvTime.getText())) {
                    showtoa("请选择上门时间");
                    return;
                }
                if (TextUtils.isEmpty(tvAdd.getText())) {
                    showtoa("请选择报修地址");
                    return;
                }
                if (TextUtils.isEmpty(etXxadd.getText())) {
                    showtoa("请输入详细地址");
                    return;
                }
                if (TextUtils.isEmpty(etMiaoshu.getText())) {
                    showtoa("请输入备注信息");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.apply, Const.POST);
                mRequest.add("order_id", getIntent().getStringExtra("order_id"));
                mRequest.add("product_id", getIntent().getStringExtra("product_id"));
                mRequest.add("address_name", etName.getText().toString());
                mRequest.add("address_tel", etPhone.getText().toString());
                mRequest.add("province_id", shengid);
                mRequest.add("city_id", shiid);
                mRequest.add("area_id", xianid);
                mRequest.add("address", etXxadd.getText().toString());
                mRequest.add("address_service_time", tvTime.getText().toString());
                mRequest.add("user_content", etMiaoshu.getText().toString());
                for (int i = 0; i < mSelectPathtwo.size(); i++) {
                    mRequest.add("img_" + i, new FileBinary(new File(mSelectPathtwo.get(i))));
                }
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        if ("1".equals(data.getCode())) {
                            startActivity(new Intent(baseContext, BaoBeiOkActivity.class)
                                    .putExtra("isshow", "1")
                                    .putExtra("tag", "2")
                            );
                            finish();
                        }
                    }

                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                showtoa(obj.getString(obj.getString("msg")));
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
                tvAdd.setText(cityList.getData().getList().get(wv1.getCurrentItem()).getName() + " "
                        + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getName() + " " + cityList.getData().getList().get(wv1.getCurrentItem()).getCityList().
                        get(wv2.getCurrentItem()).getDistrict().get(wv3.getCurrentItem()).getName());
                break;
        }
    }
}
