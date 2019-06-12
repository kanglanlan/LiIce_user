package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.meida.MyView.MyListView;
import com.meida.model.FenLeiList;
import com.meida.model.OrderInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.IntegralReward_A;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.R.id.tv_qian;
import static com.meida.share.Datas.ISRE;

public class OrderInfoActivity extends BaseActivity {

    @Bind(R.id.img_y)
    ImageView img_y;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tv_sjr)
    TextView tvSjr;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.list_item)
    MyListView listItem;
    @Bind(R.id.tv_peisongfangshi)
    TextView tvPeisongfangshi;
    @Bind(R.id.tv_fuwushang)
    TextView tvFuwushang;
    @Bind(R.id.tv_beizhu)
    TextView tv_beizhu;
    @Bind(R.id.tv_nums)
    TextView tvNums;
    @Bind(tv_qian)
    TextView tvQian;
    @Bind(R.id.tv_dingdanbianhao)
    TextView tvDingdanbianhao;
    @Bind(R.id.tv_xiadanshijian)
    TextView tvXiadanshijian;
    @Bind(R.id.tv_paytime)
    TextView tvPaytime;
    @Bind(R.id.tv_fahuo)
    TextView tvFahuo;
    @Bind(R.id.tv_dingdanstatus)
    TextView tvDingdanstatus;
    @Bind(R.id.tv_paytype)
    TextView tvPaytype;
    @Bind(R.id.viewxian)
    View viewxian;
    @Bind(R.id.tv_shifu)
    TextView tvShifu;
    @Bind(R.id.fukuan)
    TextView fukuan;
    @Bind(R.id.ll_daifukaun)
    LinearLayout llDaifukaun;
    @Bind(R.id.tv_wancheng)
    TextView tvWancheng;
    @Bind(R.id.ll_wancheng)
    LinearLayout llWancheng;
    String[] quxdatas;
    private BottomBaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
        changeTitle("订单详情");
        getorderinfo();
        img_y.setVisibility(View.GONE);
        Nonce.getpinpai(false, 7, baseContext, new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                quxdatas = new String[data.getData().getList().size()];
                for (int i = 0; i < data.getData().getList().size(); i++) {
                    quxdatas[i] = data.getData().getList().get(i).getTitle();
                }
            }
        });
    }

    @OnClick({R.id.tv_title_right, R.id.fukuan, R.id.tv_wancheng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                switch (orderInfo.getData().getStatus()) {
                    case "5":
                    case "1":
                        tvTitleRight.setText("删除订单");
                        final NormalDialog dialog = new NormalDialog(baseContext);
                        dialog.content("确认取消订单？")//
                                .style(NormalDialog.STYLE_TWO)//
                                .titleLineColor(getResources().getColor(R.color.black))
                                .title("提示")
                                .titleTextSize(23)//
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
                                        Log.i("=======", "点击确定");
                                        dialog.dismiss();
                                        Nonce.order("", getIntent().getStringExtra("id"), "2", baseContext, new Nonce.StringCallback() {
                                            @Override
                                            public void doWork(String string) {
                                                ISRE = 1;
                                                finish();
                                            }
                                        });

                                    }
                                });


                        break;
                    case "2":
                        if (quxdatas != null) {
                            showreson(quxdatas);
                        } else {
                            Nonce.getpinpai(true, 7, baseContext, new Nonce.fenleiCallback() {
                                @Override
                                public void doWork(FenLeiList data) {
                                    quxdatas = new String[data.getData().getList().size()];
                                    for (int i = 0; i < data.getData().getList().size(); i++) {
                                        quxdatas[i] = data.getData().getList().get(i).getTitle();
                                    }
                                    showreson(quxdatas);
                                }
                            });
                        }
                        break;

                }

                break;
            case R.id.fukuan:
                try {
                    startActivity(new Intent(baseContext, PayActivity.class)
                            .putExtra("id", getIntent().getStringExtra("id"))
                            .putExtra("qian", orderInfo.getData().getOrder_amount())
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_wancheng:
                Nonce.order("", getIntent().getStringExtra("id"), "4", baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {

                        //类型1购买订单，2售后服务
                        startActivity(new Intent(baseContext, IntegralReward_A.class)
                                .putExtra("id", getIntent().getStringExtra("id"))
                                .putExtra("type", "1")
                        );
                        finish();
                    }
                });
                break;
        }
    }

    /**
     * 订单详情
     */
    OrderInfo orderInfo;

    public void getorderinfo() {
        mRequest = NoHttp.createStringRequest(HttpIp.orderdetail, Const.POST);
        mRequest.add("order_id", getIntent().getStringExtra("id"));
        getRequest(new CustomHttpListener<OrderInfo>(baseContext, true, OrderInfo.class) {
            @Override
            public void doWork(OrderInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    orderInfo = data;
                    if (data.getData() != null) {
                        tvSjr.setText(data.getData().getAddress_name() + "   " + data.getData().getAddress_tel());
                        tvAdd.setText(data.getData().getArea_merger_name() + data.getData().getAddress());
                        tvPeisongfangshi.setText("配送方式：送装一体");
                        if (TextUtils.isEmpty(data.getData().getMerchant_info().getCompany_name())) {
                            tvFuwushang.setText("配送服务商：");
                        } else {
                            tvFuwushang.setText("配送服务商：" + data.getData().getMerchant_info().getCompany_name());
                        }
                        if (!TextUtils.isEmpty(data.getData().getUser_remark())) {
                            tv_beizhu.setVisibility(View.VISIBLE);
                            tv_beizhu.setText("备注：" + data.getData().getUser_remark());
                        }
                        tvNums.setText("共" + data.getData().getTotal_product_num() + "件商品 实付：");
                        tvShifu.setText("¥" + data.getData().getOrder_amount());
                        tvQian.setText(data.getData().getOrder_amount());
                        tvDingdanbianhao.setText("订单编号：" + data.getData().getOrder_id());
                        tvXiadanshijian.setText("下单时间：" + data.getData().getCreate_time());
                        tvPaytime.setText("付款时间：" + data.getData().getPay_time());
                        tvFahuo.setText("发货时间：" + data.getData().getExpress_time());
//
                        tvDingdanstatus.setText("订单状态：" + data.getData().getStatus_name());
                        listItem.setAdapter(new CommonAdapter<OrderInfo.DataBean.ListBean>(
                                baseContext, R.layout.item_gouwuche, data.getData().getList()
                        ) {
                            @Override
                            protected void convert(ViewHolder viewHolder, OrderInfo.DataBean.ListBean item, int position) {
                                viewHolder.getView(R.id.img_xz).setVisibility(View.GONE);
                                viewHolder.getView(R.id.ll_jiajian).setVisibility(View.GONE);
                                viewHolder.getView(R.id.tv_numss).setVisibility(View.VISIBLE);
                                ImageView img = viewHolder.getView(R.id.img_beihuo);
                                CommonUtil.setimg(baseContext, item.getProduct_logo(), R.drawable.moren, img);
                                viewHolder.setText(R.id.tv_name, item.getProduct_name());
                                viewHolder.setText(R.id.tv_type, item.getProduct_spec());
                                viewHolder.setText(R.id.tv_yajin, "¥" + item.getProduct_price());
                                viewHolder.setText(R.id.tv_numss, "x" + item.getProduct_num() + "台");
                            }
                        });
                        //           状态0或空为全部，1已取消2待支付3待送装4待确认5已完成
                        switch (data.getData().getStatus()) {
                            case "5":
                                tvTitleRight.setText("删除订单");
                                break;
                            case "1":
                                tvTitleRight.setText("删除订单");
                                break;
                            case "2":
                                tvTitleRight.setText("取消订单");
                                llDaifukaun.setVisibility(View.VISIBLE);
                                break;
                            case "3":
                                tvPaytime.setVisibility(View.VISIBLE);
                                tvPaytype.setVisibility(View.VISIBLE);
                                break;
                            case "4":
                                tvPaytime.setVisibility(View.VISIBLE);
                                tvFahuo.setVisibility(View.VISIBLE);
                                tvPaytype.setVisibility(View.VISIBLE);
                                llWancheng.setVisibility(View.VISIBLE);
                                break;
                        }
                        switch (data.getData().getPay_type()) {//支付方式（1微信2支付宝；3提现积分；4兑换积分）
                            case "1":
                                tvPaytype.setText("交易方式：微信");
                                break;
                            case "2":
                                tvPaytype.setText("交易方式：支付宝");
                                break;
                            case "3":
                                tvPaytype.setText("交易方式：提现积分");
                                break;
                            case "4":
                                tvPaytype.setText("交易方式：兑换积分");
                                break;
                        }

                    }
                }
            }
        }, true);
    }

    public void showreson(final String[] string) {
        final View view = View.inflate(baseContext, R.layout.popu_one, null);
        final WheelView wv1 = (WheelView) view.findViewById(R.id.wl_1);
        TextView title = (TextView) view.findViewById(R.id.tv_popu_title);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        TextView tv_queding = (TextView) view.findViewById(R.id.tv_queding);
        title.setText("取消订单");
        wv1.setViewAdapter(new ArrayWheelAdapter<String>(baseContext, string));
        dialog = new BottomBaseDialog(baseContext) {
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
                Nonce.order(string[wv1.getCurrentItem()], getIntent().getStringExtra("id"), "1", baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        ISRE = 1;
                        finish();
                    }
                });
            }
        });
    }
}
