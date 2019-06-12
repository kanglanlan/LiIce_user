package com.meida.ui.fg05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.XiangMuInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 报备的详情
 *
 * @author Administrator-LFC
 *         created at 2018/8/15 11:47
 */
public class ReportsDetail_A extends BaseActivity {

    @Bind(R.id.tv_failreason_rd)
    TextView tvFailreasonRd;
    @Bind(R.id.tv_time_rd)
    TextView tvTimeRd;
    @Bind(R.id.tv_pnum_rd)
    TextView tvPnumRd;
    @Bind(R.id.tv_pname_rd)
    TextView tvPnameRd;
    @Bind(R.id.tv_ptype_rd)
    TextView tvPtypeRd;
    @Bind(R.id.tv_parea_rd)
    TextView tvPareaRd;
    @Bind(R.id.tv_ownername_rd)
    TextView tvOwnernameRd;
    @Bind(R.id.tv_ownertel_rd)
    TextView tvOwnertelRd;
    @Bind(R.id.tv_fromname_rd)
    TextView tvFromnameRd;
    @Bind(R.id.tv_intent_rd)
    TextView tvIntentRd;
    @Bind(R.id.tv_intent_type_rd)
    TextView tvIntentTypeRd;
    @Bind(R.id.tv_paystyle_rd)
    TextView tvPaystyleRd;
    @Bind(R.id.tv_ads_rd)
    TextView tvAdsRd;
    @Bind(R.id.tv_note_rd)
    TextView tvNoteRd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_detail);
        ButterKnife.bind(this);
        initView();
        getData(true);
    }

    private void initView() {
        changeTitle("报备的详情");

        tvFailreasonRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/8/15  展示使用  后期删除掉
                baseContext.startActivity(new Intent(baseContext, RepayPlan_A.class));
            }
        });
    }
    XiangMuInfo xiangMuInfo;
    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.projectdetail, Const.POST);
        mRequest.add("project_id", getIntent().getStringExtra("id"));
        getRequest(new CustomHttpListener<XiangMuInfo>(baseContext, true, XiangMuInfo.class) {
            @Override
            public void doWork(XiangMuInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    xiangMuInfo = data;
                    tvTimeRd.setText(data.getData().getCreate_time());
                    tvPnumRd.setText(data.getData().getTransaction_number());
                    tvPnameRd.setText(data.getData().getTitle());
                    tvPtypeRd.setText(data.getData().getProperty_name());
                    tvPareaRd.setText(data.getData().getArea() + "平方米");
                    tvOwnernameRd.setText(data.getData().getAddress_name());
                    tvOwnertelRd.setText(data.getData().getAddress_tel());
                    tvFromnameRd.setText(data.getData().getNickname());
                    tvIntentRd.setText(data.getData().getBrand_name());
                    tvIntentTypeRd.setText(data.getData().getProduct_type_name());
                    tvAdsRd.setText(data.getData().getArea_merger_name() + data.getData().getAddress());
                    tvNoteRd.setText(data.getData().getContent());
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
            }
        }, isload);
    }


}
