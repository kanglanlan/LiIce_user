package com.meida.liice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PopuAreaUtils;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuanZengJifenActivity extends BaseActivity {

    @Bind(R.id.tv_leiji)
    TextView tvLeiji;
    @Bind(R.id.tv_zuanzeng)
    TextView tvZuanzeng;
    @Bind(R.id.tv_kezuanzeng)
    TextView tvKezuanzeng;
    @Bind(R.id.et_id)
    EditText etId;
    @Bind(R.id.et_qian)
    EditText etQian;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_zeng_jifen);
        ButterKnife.bind(this);
        changeTitle("转赠积分");
        tvTitleRight.setText("转赠记录");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(baseContext, ZhaunZengListActivity.class).putExtra("tag", "1"));
            }
        });
        tvLeiji.setText(PreferencesUtils.getString(baseContext, "total_score"));//补贴积分
        tvZuanzeng.setText(PreferencesUtils.getString(baseContext, "total_score_give"));//兑换积分
        tvKezuanzeng.setText(PreferencesUtils.getString(baseContext, "total_payment_score"));//补贴
    }

    @OnClick({R.id.img_del, R.id.tv_zhuanzeng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_del:
                etQian.setText("");
                break;
            case R.id.tv_zhuanzeng:
                if (TextUtils.isEmpty(etId.getText().toString())) {
                    showtoa("请输入用户ID");
                    return;
                }
                if (TextUtils.isEmpty(etQian.getText().toString())) {
                    showtoa("请输入用户积分");
                    return;
                }
                Nonce.getusername(etId.getText().toString(), baseContext, new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        if (TextUtils.isEmpty(string)) {
                            showtoa("用户不存在");
                            return;
                        }
                        final NormalDialog dialog = new NormalDialog(baseContext);
                        dialog.content("你当前正给" + string + "转赠" + etQian.getText().toString() +
                                "积分，确认无误继续操作请点确认。")//
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
                                        if ("2".equals(PreferencesUtils.getString(baseContext, "pay_pass_status"))) {
                                            PopuAreaUtils.showDialog(
                                                    baseContext,
                                                    "温馨提示",
                                                    "未设置支付密码，暂无法支付，是否现在去设置？",
                                                    "取消",
                                                    "去设置", new PopuAreaUtils.HintCallBack() {
                                                        @Override
                                                        public void doWork() {
                                                            Intent intent = new Intent(baseContext, ChangePaypswActivity.class);
                                                            intent.putExtra("tag", "1");
                                                            startActivity(intent);
                                                        }
                                                    });
                                            return;
                                        }
                                        final BottomBaseDialog dialog = new BottomBaseDialog(baseContext) {
                                            private ImageView iv_close;
                                            private TextView tv_txt, tv_forget;
                                            private GridPasswordView gpv_pwd;

                                            @Override
                                            public View onCreateView() {
                                                View view = View.inflate(baseContext, R.layout.dialog_withdraw_password, null);
                                                iv_close = (ImageView) view.findViewById(R.id.iv_dialog_withdraw_close);
                                                tv_txt = (TextView) view.findViewById(R.id.tv_dialog_withdraw_hint);
                                                gpv_pwd = (GridPasswordView) view.findViewById(R.id.gpv_dialog_withdraw_pwd);
                                                tv_forget = (TextView) view.findViewById(R.id.tv_dialog_withdraw_forget);
                                                gpv_pwd.setPasswordType(PasswordType.NUMBER);
                                                tv_txt.setText("支付" + etQian.getText().toString() + "积分");
                                                return view;
                                            }

                                            @Override
                                            public void setUiBeforShow() {

                                                gpv_pwd.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
                                                    @Override
                                                    public void onTextChanged(String psw) {
                                                    }

                                                    @Override
                                                    public void onInputFinish(String psw) {
                                                        dismiss();
                                                        zhuanzeng(psw);
                                                    }
                                                });
                                                iv_close.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dismiss();
                                                    }
                                                });

                                                tv_forget.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dismiss();//支付密码
                                                        Intent intent = new Intent(baseContext, ChangePaypswActivity.class);
                                                        intent.putExtra("tag", "1");
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                        };
                                        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                            @Override
                                            public void onShow(DialogInterface dialogInterface) {
                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                            }
                                        });
                                        dialog.show();
                                    }
                                });

                    }
                });


                break;
        }
    }


    public void zhuanzeng(String user_pass) {
        mRequest = NoHttp.createStringRequest(HttpIp.transfer, Const.POST);
        mRequest.add("user_id", etId.getText().toString());
        mRequest.add("score", etQian.getText().toString());
        mRequest.add("pay_pass", user_pass);
        getRequest(new CustomHttpListener<Login>(baseContext, true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                if ("1".equals(data.getCode())) {
                    startActivity(new Intent(baseContext, ZhuanZengOkActivity.class).putExtra("jifen", etQian.getText().toString()));
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
    }
}
