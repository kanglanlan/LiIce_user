package com.meida.liice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.meida.model.MoRenAdd;
import com.meida.model.PayInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PopuAreaUtils;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISRE;


public class QueRenOrderjifenActivity extends BaseActivity {

    @Bind(R.id.tv_sjr)
    TextView tvSjr;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.ll_addinfo)
    LinearLayout ll_addinfo;
    @Bind(R.id.ll_add)
    LinearLayout llAdd;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_beizhu)
    EditText etBeizhu;
    @Bind(R.id.duihuanjifen)
    RadioButton duihuanjifen;
    @Bind(R.id.rbbutie)
    RadioButton rbbutie;
    @Bind(R.id.tv_duihuanyue)
    TextView tvDuihuanyue;
    @Bind(R.id.tv_butieyue)
    TextView tvButieyue;
    @Bind(R.id.tv_jiage)
    TextView tvJiage;
    @Bind(R.id.tv_tianjiaadd)
    TextView tvTianjiaadd;
    @Bind(R.id.tv_nums)
    TextView tvNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_orderjifen);
        ButterKnife.bind(this);
        changeTitle("确认订单");
        getdata();
        tvName.setText(getIntent().getStringExtra("name"));
        CommonUtil.setimg(baseContext, getIntent().getStringExtra("img"), R.drawable.moren, img);
        duihuanjifen.setText("兑换积分：" + getIntent().getStringExtra("duihuan") + "积分");
        rbbutie.setText("补贴积分：" + getIntent().getStringExtra("butie") + "积分");
        double duihuan = Integer.parseInt(tvNums.getText().toString()) *
                Double.parseDouble(getIntent().getStringExtra("duihuan"));
        tvJiage.setText(duihuan + "");
        tvDuihuanyue.setText("兑换积分余额：" + PreferencesUtils.getString(baseContext, "score_exchange") + "积分");//兑换积分
        tvButieyue.setText("补贴积分余额：" + PreferencesUtils.getString(baseContext, "total_payment_score") + "积分");//补贴
        rbbutie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double butie = Integer.parseInt(tvNums.getText().toString()) *
                        Double.parseDouble(getIntent().getStringExtra("butie"));
                tvJiage.setText(butie + "");
            }
        });
        duihuanjifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double butie = Integer.parseInt(tvNums.getText().toString()) *
                        Double.parseDouble(getIntent().getStringExtra("duihuan"));
                tvJiage.setText(butie + "");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ISRE == 1 || TextUtils.isEmpty(addid)) {
            ISRE = 0;
            getdata();
        }
    }

    @OnClick({R.id.ll_add, R.id.tv_zhifu, R.id.tv_tianjiaadd, R.id.tv_jian, R.id.tv_jia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jian:
                if (Integer.parseInt(tvNums.getText().toString()) > 1) {
                    tvNums.setText((Integer.parseInt(tvNums.getText().toString()) - 1) + "");
                    if (rbbutie.isChecked()) {
                        double butie = Integer.parseInt(tvNums.getText().toString()) *
                                Double.parseDouble(getIntent().getStringExtra("butie"));
                        tvJiage.setText(butie + "");
                    } else {
                        double butie = Integer.parseInt(tvNums.getText().toString()) *
                                Double.parseDouble(getIntent().getStringExtra("duihuan"));
                        tvJiage.setText(butie + "");
                    }
                }
                break;
            case R.id.tv_jia:
                tvNums.setText((Integer.parseInt(tvNums.getText().toString()) + 1) + "");
                if (rbbutie.isChecked()) {
                    double butie = Integer.parseInt(tvNums.getText().toString()) *
                            Double.parseDouble(getIntent().getStringExtra("butie"));
                    tvJiage.setText(butie + "");
                } else {
                    double butie = Integer.parseInt(tvNums.getText().toString()) *
                            Double.parseDouble(getIntent().getStringExtra("duihuan"));
                    tvJiage.setText(butie + "");
                }
                break;
            case R.id.tv_tianjiaadd:
//                break;
                startActivityForResult(new Intent(baseContext, MyAddActivity.class).putExtra("tag", "1"), 1);
                break;
            case R.id.ll_add:
                startActivityForResult(new Intent(baseContext, MyAddActivity.class).putExtra("tag", "1"), 1);
                break;
            case R.id.tv_zhifu:
                if(TextUtils.isEmpty(addid))
                {
                    showtoa("请选择地址");
                    return;
                }
                if (rbbutie.isChecked()) {
                   if(Double.parseDouble(tvJiage.getText().toString())>Double.parseDouble(PreferencesUtils.getString(baseContext, "total_payment_score")))
                   {
                       showtoa("积分不足");
                       return;
                   }
                } else {
                    if(Double.parseDouble(tvJiage.getText().toString())>Double.parseDouble(PreferencesUtils.getString(baseContext, "score_exchange")))
                    {
                        showtoa("积分不足");
                        return;
                    }
                }
                //pay_pass	string	是否设置支付密码 0 否 1 是
                //          未设置支付密码
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
                        tv_txt.setText("支付" + tvJiage.getText().toString() + "积分");
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
        mRequest = NoHttp.createStringRequest(HttpIp.pay, Const.POST);
        mRequest.add("product_type", 2);//1商城商品2兑换商品
        mRequest.add("product_info", "[{\"product_id\":" + getIntent().getStringExtra("id") + ",\"product_num\":1}]");

        if (rbbutie.isChecked()) {
            mRequest.add("pay_type", 3);//	支付类型（1微信2支付宝3提现积分4兑换积分）
        } else {
            mRequest.add("pay_type", 4);
        }
        mRequest.add("address_id", addid);
        mRequest.add("user_remark", etBeizhu.getText().toString());
        mRequest.add("pay_pass", pay_pass);
        mRequest.add("is_cart_del", 0);//0或空表示不删除购物车，1删除
        getRequest(new CustomHttpListener<PayInfo>(baseContext, true, PayInfo.class) {
            @Override
            public void doWork(PayInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    String type;
                    if (rbbutie.isChecked()) {
                        type = "补贴积分";
                    } else {
                        type = "兑换积分";
                    }
                    //使用Date
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    startActivity(new Intent(baseContext, DuiHuanOkActivity.class)
                            .putExtra("name", tvName.getText().toString())
                            .putExtra("oid", data.getData().getOrder_id())
                            .putExtra("type", type)
                            .putExtra("time", sdf.format(d))
                    );
                    finish();
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
                        showtoa(obj.getString("msg"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

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
                            setadd(data.getData().getId(), data.getData().getAddress_name() + "  " + data.getData().getAddress_tel()
                                    , data.getData().getArea_merger_name() + data.getData().getAddress());
                        } else {
                            llAdd.setVisibility(View.VISIBLE);
                            tvTianjiaadd.setVisibility(View.VISIBLE);
                            ll_addinfo.setVisibility(View.GONE);
                            addid = "";
                        }
                    } else {
                        addid = "";
                        llAdd.setVisibility(View.VISIBLE);
                        tvTianjiaadd.setVisibility(View.VISIBLE);
                        ll_addinfo.setVisibility(View.GONE);
                    }
                }
            }
        }, true);
    }

    String addid;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            setadd(data.getStringExtra("id"), data.getStringExtra("name") + "  " + data.getStringExtra("tel")
                    , data.getStringExtra("add"));
        }
    }

    public void setadd(String id, String name, String add) {
        addid = id;
        llAdd.setVisibility(View.VISIBLE);
        ll_addinfo.setVisibility(View.VISIBLE);
        tvTianjiaadd.setVisibility(View.GONE);
        tvSjr.setText("收货人:" + name);
        tvAdd.setText(add);
    }


}
