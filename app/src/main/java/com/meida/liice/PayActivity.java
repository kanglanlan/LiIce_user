package com.meida.liice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.model.PayInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PayTool;
import com.meida.utils.WXPayTools;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISRE;
import static com.meida.share.Datas.MONEY;
import static com.meida.share.Datas.PAYTYPE;

public class PayActivity extends BaseActivity {

    @Bind(R.id.tv_qian)
    TextView tvQian;
    @Bind(R.id.img_zhifubao)
    ImageView imgZhifubao;
    @Bind(R.id.img_weixin)
    ImageView imgWeixin;
    private int pay_type = 2;////支付类型（1微信2支付宝3提现积分4兑换积分）
public static Activity paytyoe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        changeTitle("选择支付方式");
        paytyoe=this;
        tvQian.setText("¥" + getIntent().getStringExtra("qian"));
    }

    @OnClick({R.id.ll_zhifubao, R.id.ll_weixin, R.id.bt_pay})
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.bt_pay:
                mRequest = NoHttp.createStringRequest(HttpIp.get_pay_info, Const.POST);
                mRequest.add("pay_type", pay_type);//	支付类型（1微信2支付宝3提现积分4兑换积分）
                mRequest.add("order_id", getIntent().getStringExtra("id"));
                getRequest(new CustomHttpListener<PayInfo>(baseContext, true, PayInfo.class) {
                    @Override
                    public void doWork(PayInfo data, String code) {
                        if ("1".equals(data.getCode())) {
                            switch (pay_type) {//1微信2支付宝3提现积分4兑换积分）
                                case 1:
                                    PAYTYPE = 2;
                                    MONEY= getIntent().getStringExtra("qian");
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
                    ISRE = 1;
                    finish();
                    break;
                case 1:
                    CommonUtil.showToask(baseContext, "支付失败！");
                    break;
            }
        }
    };
}
