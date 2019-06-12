package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISRE;

public class TianJiaYinHangKaActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    EditText tvName;
    @Bind(R.id.tv_kahao)
    EditText tvKahao;
    @Bind(R.id.tv_yhk)
    TextView tvYhk;
    @Bind(R.id.tv_huhang)
    EditText tvHuhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_jia_yin_hang_ka);
        ButterKnife.bind(this);
        changeTitle("我的银行卡");
        if(!TextUtils.isEmpty(getIntent().getStringExtra("id")))
        {
            tvName.setText(getIntent().getStringExtra("name"));
            tvKahao.setText(getIntent().getStringExtra("num"));
            tvYhk.setText(getIntent().getStringExtra("bankname"));
            tvHuhang.setText(getIntent().getStringExtra("subname"));
            bank_id=getIntent().getStringExtra("cardid");
            card_logo=getIntent().getStringExtra("logo");
        }
    }

    @OnClick({R.id.tv_yhk, R.id.bt_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yhk:
                startActivityForResult(new Intent(baseContext, YinHangKaActivity.class), 1);
                break;
            case R.id.bt_tijiao:
                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    showtoa("请输入持卡姓名");
                    return;
                }
                if (TextUtils.isEmpty(tvKahao.getText().toString())) {
                    showtoa("请输入银行卡号");
                    return;
                }
                if (TextUtils.isEmpty(tvYhk.getText().toString())) {
                    showtoa("请选择银行");
                    return;
                }
                if (TextUtils.isEmpty(tvHuhang.getText().toString())) {
                    showtoa("请填写开户行");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.bankCard, Const.POST);
                if (!TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
                    mRequest.add("id", getIntent().getStringExtra("id"));
                }
                mRequest.add("card_name", tvName.getText().toString());
                mRequest.add("card_number", tvKahao.getText().toString());
                mRequest.add("sub_bank_name", tvHuhang.getText().toString());
                mRequest.add("bank_id", bank_id);
                mRequest.add("card_logo", card_logo);
                getRequest(new CustomHttpListener<Login>(baseContext, true, Login.class) {
                    @Override
                    public void doWork(Login data, String code) {
                        if ("1".equals(data.getCode())) {
                            ISRE=1;
                            finish();
                        }
                    }
                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                showtoa(obj.getString("msg"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
                break;
        }
    }

    String bank_id = "";
    String card_logo = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bank_id = data.getStringExtra("id");
            card_logo = data.getStringExtra("card_logo");
            tvYhk.setText(data.getStringExtra("name"));
        }
    }
}
