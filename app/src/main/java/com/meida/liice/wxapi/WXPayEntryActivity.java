package com.meida.liice.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.liice.ZhiFuOkActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.ButterKnife;

import static com.meida.liice.GouWuCheActivity.gouwu;
import static com.meida.liice.PayActivity.paytyoe;
import static com.meida.liice.QueRenOrderActivity.querenorder;
import static com.meida.share.Datas.APPID;
import static com.meida.share.Datas.MONEY;
import static com.meida.share.Datas.PAYTYPE;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_kong);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, APPID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {//成功
            showtoa("支付成功");
            switch (PAYTYPE) {//2下单购物
                case 1:
//                    finish();
                    break;
                case 2:
                    if (gouwu != null) {
                        gouwu.finish();
                    }
                    if (querenorder != null) {
                        querenorder.finish();
                    }
                    if (paytyoe != null) {
                        paytyoe.finish();
                    }
                    startActivity(new Intent(baseContext, ZhiFuOkActivity.class).putExtra("price", MONEY));
                    finish();
                    break;

            }
        } else {
            showtoa("支付失败");
            finish();
        }

    }


}