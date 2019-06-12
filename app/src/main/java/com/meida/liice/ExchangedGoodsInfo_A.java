package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.MyView.MyListView;
import com.meida.model.OrderInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISRE;

public class ExchangedGoodsInfo_A extends BaseActivity {

    @Bind(R.id.tv_sjr)
    TextView tvSjr;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.list_item)
    MyListView listItem;
    @Bind(R.id.tv_nums)
    TextView tvNums;
    @Bind(R.id.tv_qian)
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
    @Bind(R.id.tv_querenshouhuo)
    TextView tvQuerenshouhuo;
    @Bind(R.id.tv_tixing)
    TextView tvTixing;
    @Bind(R.id.tv_wuliuname)
    TextView tv_wuliuname;
    @Bind(R.id.tv_wulihao)
    TextView tv_wulihao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchanged_goods_info_);
        ButterKnife.bind(this);
        changeTitle(getIntent().getStringExtra("title"));
        getorderinfo();
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
//                        tvFuwushang.setText("配送服务商：" + data.getData().getArea_merger_name());
                        tvNums.setText("共" + data.getData().getTotal_product_num() + "件商品 实付：");
                        tvQian.setText("¥" + data.getData().getOrder_amount()+"积分");
                        tvDingdanbianhao.setText("订单编号：" + data.getData().getOrder_id());
                        tvXiadanshijian.setText("下单时间：" + data.getData().getCreate_time());
                        tvPaytime.setText("付款时间：" + data.getData().getPay_time());
                        tvFahuo.setText("发货时间：" + data.getData().getExpress_time());
                        tv_wulihao.setText("物流单号：" + data.getData().getExpress_number());
                        tv_wuliuname.setText("物流名称：" + data.getData().getExpress_company());
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
                            case "3":
                                tvTixing.setVisibility(View.VISIBLE);
                                break;
                            case "4":
                                tv_wulihao.setVisibility(View.VISIBLE);
                                tv_wuliuname.setVisibility(View.VISIBLE);
                                tvFahuo.setVisibility(View.VISIBLE);
                                tvQuerenshouhuo.setVisibility(View.VISIBLE);
                                break;
                            case "5":
                                tv_wulihao.setVisibility(View.VISIBLE);
                                tv_wuliuname.setVisibility(View.VISIBLE);
                                tvFahuo.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.tv_querenshouhuo, R.id.tv_tixing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_querenshouhuo:
                Nonce.order("", orderInfo.getData().getOrder_id(), "4", baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        ISRE = 1;
                        finish();
                    }
                });
                break;
            case R.id.tv_tixing:

                Nonce.order("", orderInfo.getData().getOrder_id(), "3", baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                    }
                });
                break;
        }
    }
}
