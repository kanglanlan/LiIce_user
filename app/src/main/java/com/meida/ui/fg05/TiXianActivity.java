package com.meida.ui.fg05;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.meida.liice.*;
import com.meida.model.Cardlist;
import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PopuAreaUtils;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {

    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tv_renzheng)
    TextView tvRenzheng;
    @Bind(R.id.ll_renzheng)
    LinearLayout llRenzheng;
    @Bind(R.id.et_qian)
    EditText etQian;
    @Bind(R.id.tv_yue)
    TextView tvYue;
    @Bind(R.id.tv_daozhang)
    TextView tvDaozhang;
    @Bind(R.id.tv_koushui)
    TextView tvKoushui;
    @Bind(R.id.tv_kouqian)
    TextView tvKouqian;
    @Bind(R.id.tv_zuiduojifen)
    TextView tvZuiduojifen;
    @Bind(R.id.tv_shouxufeibfb)
    TextView tvShouxufeibfb;
    @Bind(R.id.yinghangka)
    ImageView yinghangka;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_tianjiayhk)
    TextView tv_tianjiayhk;
    @Bind(R.id.tv_kahao)
    TextView tvKahao;
    @Bind(R.id.ll_ka)
    LinearLayout llKa;
    private final int DECIMAL_DIGITS = 2;//小数的位数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian);
        ButterKnife.bind(this);
        changeTitle("提现");
        tvTitleRight.setText("提现规则");
        //                is_withdraw_card_check 提现是否需要实名：若有参数且为1，需要首先实名认证通过
        if ("1".equals(PreferencesUtils.getString(baseContext, "is_withdraw_card_check"))) {
            //0未认证1认证中2认证通过3认证失败
            if ("1".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
                llRenzheng.setVisibility(View.VISIBLE);
                tvRenzheng.setBackgroundResource(R.drawable.huibt);
                tvRenzheng.setText("认证中");
            }
            if ("3".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
                llRenzheng.setVisibility(View.VISIBLE);
                tvRenzheng.setBackgroundResource(R.drawable.huibt);
                tvRenzheng.setText("认证失败");
            }
            if ("0".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
                llRenzheng.setVisibility(View.VISIBLE);
                tvRenzheng.setText("立即认证");
            }
        }
        getata();

        tvYue.setText("可提现余额积分" + PreferencesUtils.getString(baseContext, "score"));
        tvZuiduojifen.setText("2、单笔提现最多提现" + PreferencesUtils.getString(baseContext, "withdraw_high") + "积分");
        tvShouxufeibfb.setText("3、每笔提现扣除" + PreferencesUtils.getString(baseContext, "withdraw_charge_user") + "%手续费");
        tvKoushui.setText("代扣税(" + PreferencesUtils.getString(baseContext, "withdraw_charge_user") + "%)");
        etQian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                        etQian.setText(s);
                        etQian.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etQian.setText(s);
                    etQian.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etQian.setText(s.subSequence(0, 1));
                        etQian.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (TextUtils.isEmpty(etQian.getText().toString())) {
                        tvKouqian.setText("-" + 0);
                        tvDaozhang.setText("0");
                    } else {
                        double koushui = Double.parseDouble(PreferencesUtils.getString(baseContext, "withdraw_charge_merchant"))
                                * Double.parseDouble(etQian.getText().toString()) / 100;
                        tvKouqian.setText("-" + koushui);
                        tvDaozhang.setText((Double.parseDouble(etQian.getText().toString()) - koushui) + "");
                    }
                } catch (Exception e) {
                    showtoa("请输入正确金额");
                    e.printStackTrace();
                }
            }
        });
    }

    String bank_card_id = "";

    public void getata() {
        mRequest = NoHttp.createStringRequest(HttpIp.bankCardList, Const.POST);
        mRequest.add("per_page", 50);
        getRequest(new CustomHttpListener<Cardlist>(baseContext, true, Cardlist.class) {
            @Override
            public void doWork(Cardlist data, String code) {
                if ("1".equals(data.getCode())) {
                    if (data.getData().getData().size() != 0) {
                        bank_card_id = data.getData().getData().get(0).getId();
                        tv_tianjiayhk.setVisibility(View.GONE);
                        llKa.setVisibility(View.VISIBLE);
                        CommonUtil.setimg(baseContext, data.getData().getData().get(0).getLogo(),
                                R.drawable.moren, yinghangka);
                        tvName.setText(data.getData().getData().get(0).getBank_name());
                        tvKahao.setText(data.getData().getData().get(0).getCard_number());
                    }
                }
            }
        }, true);
    }

    @OnClick({R.id.tv_title_right, R.id.tv_renzheng, R.id.ll_ka, R.id.tv_tianjiayhk, R.id.img_del, R.id.bt_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                startActivity(new Intent(baseContext, ZuLinActivity.class).putExtra("tag", "105"));
                break;
            case R.id.tv_renzheng:
                if ("0".equals(PreferencesUtils.getString(baseContext, "card_status")) ||
                        "3".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
                    StartActivity(ShiMingRenZhengActivity.class);
                }
                break;
            case R.id.tv_tianjiayhk:
                startActivityForResult(new Intent(baseContext, com.meida.liice.MyYinHangKaActivity.class), 1);
                break;
            case R.id.ll_ka:
                startActivityForResult(new Intent(baseContext, com.meida.liice.MyYinHangKaActivity.class), 1);
                break;
            case R.id.img_del:
                etQian.setText("");
                break;
            case R.id.bt_tixian:
                if ("1".equals(PreferencesUtils.getString(baseContext, "is_withdraw_card_check"))) {

                    if (!"2".equals(PreferencesUtils.getString(baseContext, "card_status"))) {
                        showtoa("请实名认证");
                        return;
                    }
                }
                if (TextUtils.isEmpty(etQian.getText().toString())) {
                    showtoa("请输入提现金额");
                    return;
                }
                if (Double.parseDouble(etQian.getText().toString()) > Double.parseDouble(PreferencesUtils.getString(baseContext, "score"))) {
                    showtoa("余额不足");
                    return;
                }
                if (Double.parseDouble(etQian.getText().toString()) > Double.parseDouble(PreferencesUtils.getString(baseContext, "withdraw_high"))) {
                    showtoa("超出提现最大额度");
                    return;
                }
                if (Double.parseDouble(etQian.getText().toString()) < Double.parseDouble(PreferencesUtils.getString(baseContext, "withdraw_low"))) {
                    showtoa("低于提现最小额度");
                    return;
                }
                if ("2".equals(PreferencesUtils.getString(baseContext, "pay_pass_status"))) {
                    PopuAreaUtils.showDialog(
                            this,
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
                                zhifu(psw);
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
                break;
        }
    }

    public void zhifu(String pay_pass) {
        mRequest = NoHttp.createStringRequest(HttpIp.withdrawapply, Const.POST);
        mRequest.add("bank_card_id", bank_card_id);
        mRequest.add("score", etQian.getText().toString());
        mRequest.add("pay_pass", pay_pass);
        getRequest(new CustomHttpListener<Login>(baseContext, true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                if ("1".equals(data.getCode())) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bank_card_id = data.getStringExtra("id");
            tv_tianjiayhk.setVisibility(View.GONE);
            llKa.setVisibility(View.VISIBLE);
            CommonUtil.setimg(baseContext, data.getStringExtra("card_logo"),
                    R.drawable.moren, yinghangka);
            tvName.setText(data.getStringExtra("name"));
            tvKahao.setText(data.getStringExtra("num"));
        }
    }
}
