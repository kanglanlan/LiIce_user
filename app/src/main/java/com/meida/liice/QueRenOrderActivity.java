package com.meida.liice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.MyView.MyListView;
import com.meida.model.Cars;
import com.meida.model.MoRenAdd;
import com.meida.model.PayInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PayTool;
import com.meida.utils.WXPayTools;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.GouWuCheActivity.gouwu;
import static com.meida.liice.R.id.tv_tianjiaadd;
import static com.meida.share.Datas.ISRE;
import static com.meida.share.Datas.MONEY;
import static com.meida.share.Datas.PAYTYPE;
import static com.meida.utils.CommonUtil.getmoreshop;
import static com.meida.utils.CommonUtil.putshop;

public class QueRenOrderActivity extends BaseActivity {

    @Bind(tv_tianjiaadd)
    TextView tvTianjiaadd;
    @Bind(R.id.tv_sjr)
    TextView tvSjr;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.ll_add)
    LinearLayout llAdd;
    @Bind(R.id.ll_addinfo)
    LinearLayout ll_addinfo;
    @Bind(R.id.list_item)
    MyListView listItem;
    @Bind(R.id.tv_peisong)
    TextView tvPeisong;
    @Bind(R.id.et_beizhu)
    EditText etBeizhu;
    @Bind(R.id.tv_fuwushang)
    TextView tvFuwushang;
    @Bind(R.id.tv_nums)
    TextView tvNums;
    @Bind(R.id.tv_qian)
    TextView tvQian;
    @Bind(R.id.img_zhifubao)
    ImageView imgZhifubao;
    @Bind(R.id.img_weixin)
    ImageView imgWeixin;
    @Bind(R.id.tv_jiage)
    TextView tvJiage;
    ArrayList<Cars.DataBean.ListBean> datas = new ArrayList<>();
    private int pay_type = 2;////支付类型（1微信2支付宝3提现积分4兑换积分）
    public static Activity querenorder;

    @Override
    protected void onRestart() {
        super.onRestart();
//        if (ISRE == 1 || TextUtils.isEmpty(addid)) {
            ISRE = 0;
            getdata();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_order);
        ButterKnife.bind(this);
        changeTitle("确认订单");
        querenorder = this;
        getdata();
        tvNums.setText("共" + getIntent().getStringExtra("allnums") + "台商品， 小计：");
        tvQian.setText(getIntent().getStringExtra("qian"));
        MONEY = getIntent().getStringExtra("qian");
        tvJiage.setText(getIntent().getStringExtra("qian"));
        Bundle bundleObject = getIntent().getExtras();
        ArrayList<Cars.DataBean.ListBean> data = (ArrayList<Cars.DataBean.ListBean>) bundleObject.getSerializable("list");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCheck() == 1) {
                datas.add(data.get(i));
            }
        }
        listItem.setAdapter(new CommonAdapter<Cars.DataBean.ListBean>(baseContext, R.layout.item_gouwuche, datas) {
            @Override
            protected void convert(ViewHolder viewHolder, Cars.DataBean.ListBean item, int position) {
                viewHolder.getView(R.id.img_xz).setVisibility(View.GONE);
                viewHolder.getView(R.id.ll_jiajian).setVisibility(View.GONE);
                viewHolder.getView(R.id.tv_numss).setVisibility(View.VISIBLE);
                ImageView img = viewHolder.getView(R.id.img_beihuo);
                CommonUtil.setimg(baseContext, item.getLogo(), R.drawable.moren, img);
                viewHolder.setText(R.id.tv_name, item.getTitle());
                viewHolder.setText(R.id.tv_type, item.getSpec());
                viewHolder.setText(R.id.tv_yajin, "¥" + item.getPrice());
                viewHolder.setText(R.id.tv_numss, "x" + item.getProduct_num() + "台");
            }
        });

    }

    @OnClick({R.id.tv_tianjiaadd, R.id.ll_add, R.id.ll_fuwushang, R.id.ll_zhifubao, R.id.ll_weixin, R.id.tv_zhifu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tianjiaadd:
//                break;
                startActivityForResult(new Intent(baseContext, MyAddActivity.class).putExtra("tag", "1"), 1);
                break;
            case R.id.ll_add:
                startActivityForResult(new Intent(baseContext, MyAddActivity.class).putExtra("tag", "1"), 1);
                break;
            case R.id.ll_fuwushang:
                startActivityForResult(new Intent(baseContext, XuanZeFuWuShangActivity.class)
                                .putExtra("city_id", city_id)
                                .putExtra("area_id", area_id)
                                .putExtra("product_info", product_info)
                        , 2);
                break;
            case R.id.ll_zhifubao:
                pay_type = 2;////支付类型（1微信2支付宝3提现积分4兑换积分）
                imgWeixin.setImageResource(R.drawable.ico_lb026);
                imgZhifubao.setImageResource(R.drawable.ico_lb027);
                break;
            case R.id.ll_weixin:
                imgZhifubao.setImageResource(R.drawable.ico_lb026);
                imgWeixin.setImageResource(R.drawable.ico_lb027);
                pay_type = 1;////支付类型（1微信2支付宝3提现积分4兑换积分）
                break;
            case R.id.tv_zhifu:
                if (TextUtils.isEmpty(addid)) {
                    showtoa("请选择收货地址");
                    return;
                }
                if (TextUtils.isEmpty(merchant_id)) {
                    showtoa("请选择服务商");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.pay, Const.POST);
                mRequest.add("merchant_id", merchant_id);
                mRequest.add("product_type", 1);//1商城商品2兑换商品
                if (datas.size() == 1) {
                    mRequest.add("product_info", putshop(datas.get(0).getProduct_num(), datas.get(0).getProduct_id()));
                } else {
                    mRequest.add("product_info", getmoreshop(datas));
                }//支付类型（1微信2支付宝3提现积分4兑换积分）
                mRequest.add("pay_type", pay_type);//	支付类型（1微信2支付宝3提现积分4兑换积分）
                mRequest.add("address_id", addid);
                mRequest.add("user_remark", etBeizhu.getText().toString());
                mRequest.add("is_cart_del", 1);//0或空表示不删除购物车，1删除
                getRequest(new CustomHttpListener<PayInfo>(baseContext, true, PayInfo.class) {
                    @Override
                    public void doWork(PayInfo data, String code) {
                        if ("1".equals(data.getCode())) {
                            switch (pay_type) {//1微信2支付宝3提现积分4兑换积分）
                                case 1:
                                    PAYTYPE = 2;
                                    WXPayTools.getInstance().WeixinPay(baseContext, data.getData().getWechat());
                                    break;
                                case 2:
                                    PayTool.getInstance().startPay(baseContext, new PayTool.PayCallback() {
                                        @Override
                                        public void doWork(String payResult) {
                                            if (TextUtils.equals("9000", payResult)) {
                                                handler.sendEmptyMessage(0);
                                            } else {
                                                handler.sendEmptyMessage(1);
                                            }
                                        }
                                    }, data.getData().getAlipay());
                                    break;
                            }
                        }
                    }
                }, true);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    CommonUtil.showToask(baseContext, "支付成功！");
                    if (gouwu != null) {
                        gouwu.finish();
                    }
                    startActivity(new Intent(baseContext, ZhiFuOkActivity.class).putExtra("price", getIntent().getStringExtra("qian")));
                    finish();
                    break;
                case 1:
                    CommonUtil.showToask(baseContext, "支付失败！");
                    break;
            }
        }
    };

    public void getdata() {
        mRequest = NoHttp.createStringRequest(HttpIp.getAddress, Const.POST);
        getRequest(new CustomHttpListener<MoRenAdd>(baseContext, true, MoRenAdd.class) {
            @Override
            public void doWork(MoRenAdd data, String code) {
                if ("1".equals(data.getCode())) {
                    if (data.getData() != null) {
                        if (!TextUtils.isEmpty(data.getData().getId())) {
                            llAdd.setVisibility(View.VISIBLE);
                            ll_addinfo.setVisibility(View.VISIBLE);
                            tvTianjiaadd.setVisibility(View.GONE);

                            if (!addid.equals(data.getData().getId())) {
                                merchant_id = "";
                                tvFuwushang.setText("");
                            }
                            addid = data.getData().getId();

                            city_id = data.getData().getCity_id();
                            area_id = data.getData().getArea_id();
                            if (datas.size() == 1) {
                                product_info = CommonUtil.putshop(datas.get(0).getProduct_num(), datas.get(0).getProduct_id());
                            } else {
                                product_info = CommonUtil.getmoreshop(datas);
                            }
                            setadd(data.getData().getAddress_name() + "  " + data.getData().getAddress_tel()
                                    , data.getData().getArea_merger_name() + data.getData().getAddress());
                        } else {
                            llAdd.setVisibility(View.VISIBLE);
                            tvTianjiaadd.setVisibility(View.VISIBLE);
                            ll_addinfo.setVisibility(View.GONE);
                        }
                    } else {
                        llAdd.setVisibility(View.VISIBLE);
                        tvTianjiaadd.setVisibility(View.VISIBLE);
                        ll_addinfo.setVisibility(View.GONE);
                    }
                }
            }
        }, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                addid = data.getStringExtra("id");
                city_id = data.getStringExtra("shiid");
                area_id = data.getStringExtra("xianid");
                if (datas.size() == 1) {
                    product_info = CommonUtil.putshop(datas.get(0).getProduct_num(), datas.get(0).getProduct_id());
                } else {
                    product_info = CommonUtil.getmoreshop(datas);
                }
                setadd(data.getStringExtra("name") + "  " + data.getStringExtra("tel")
                        , data.getStringExtra("add"));
            }
            if (requestCode == 2) {
                merchant_id = data.getStringExtra("id");
                tvFuwushang.setText(data.getStringExtra("name"));
            }
        }
    }

    String merchant_id = "";//服务商id
    String addid = "";//地址id
    String city_id = "";//城市id
    String product_info = "";//地址id
    String area_id = "";//区域id

    public void setadd(String name, String add) {
        llAdd.setVisibility(View.VISIBLE);
        ll_addinfo.setVisibility(View.VISIBLE);
        tvTianjiaadd.setVisibility(View.GONE);
        tvSjr.setText("收货人:" + name);
        tvAdd.setText(add);
    }
}
