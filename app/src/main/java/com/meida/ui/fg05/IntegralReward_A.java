package com.meida.ui.fg05;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.meida.liice.BaseActivity;
import com.meida.liice.ChangePaypswActivity;
import com.meida.liice.IntegralRewardokActivity;
import com.meida.liice.R;
import com.meida.model.FenLeiList;
import com.meida.model.MoRenAdd;
import com.meida.model.TestDataM;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.utils.PopuAreaUtils;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 积分打赏
 *
 * @author Administrator-LFC
 *         created at 2018/8/17 9:41
 */
public class IntegralReward_A extends BaseActivity {

    @Bind(R.id.rlv_ir)
    RecyclerView rlvIr;
    @Bind(R.id.lay_scro)
    NestedScrollView layScro;
    @Bind(R.id.btn_pay_ir)
    Button btnPayIr;
    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    private Adapter_Integral adapter;
    private List<TestDataM> list_data = new ArrayList<>();//数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_reward);
        ButterKnife.bind(this);
        initView();
        getData(true);

    }

    private void initView() {
        changeTitle("积分打赏");
        gridLayoutManager = new GridLayoutManager(baseContext, 3);
        rlvIr.setLayoutManager(gridLayoutManager);
        adapter = new Adapter_Integral(baseContext, R.layout.item_integrals, list_data);
        rlvIr.setAdapter(adapter);

    }

    String scor = "";

    private void getData(boolean isload) {
        Nonce.getpinpai(true,5, baseContext, new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                for (int i = 0; i < data.getData().getList().size(); i++) {
                    TestDataM testDataM = new TestDataM(data.getData().getList().get(i).getTitle(), false);
                    list_data.add(testDataM);
                }
                list_data.get(0).setSelect(true);
                scor = list_data.get(0).getStr_name();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick(R.id.btn_pay_ir)
    public void onViewClicked() {
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
                tv_txt.setText("支付" + scor + "积分");
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
    }

    private void zhifu(String pay_pass) {
        mRequest = NoHttp.createStringRequest(HttpIp.tips, Const.POST);
        mRequest.add("type", getIntent().getStringExtra("type"));//类型1购买订单，2售后服务
        mRequest.add("order_id", getIntent().getStringExtra("id"));
        mRequest.add("score", scor);
        mRequest.add("pay_pass", pay_pass);
        getRequest(new CustomHttpListener<MoRenAdd>(baseContext, true, MoRenAdd.class) {
            @Override
            public void doWork(MoRenAdd data, String code) {
                if ("1".equals(data.getCode())) {
                    StartActivity(IntegralRewardokActivity.class);
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

    /**
     * 积分
     */
    public class Adapter_Integral extends CommonAdapter<TestDataM> {
        public Adapter_Integral(Context context, int layoutId, List<TestDataM> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final TestDataM s, final int position) {

            TextView tv = holder.getView(R.id.tv_itemi);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
            lp.width = (CommonUtil.getScreenWidth(baseContext) - CommonUtil.dip2px(baseContext, 15 * 2 + 20 * 2)) / 3;
            lp.height = (int) (lp.width * 0.46);
            tv.setLayoutParams(lp);
            tv.setText(s.getStr_name() + "积分");
            if (s.isSelect()) {
                tv.setTextColor(getResources().getColor(R.color.white));
                holder.itemView.setBackgroundResource(R.drawable.bg_round_org_2);
            } else {
                tv.setTextColor(getResources().getColor(R.color.black));
                holder.itemView.setBackgroundResource(R.drawable.bg_round_line_2);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, position + "");
                    for (int i = 0; i < list_data.size(); i++) {
                        list_data.get(i).setSelect(false);
                    }
                    list_data.get(position).setSelect(!s.isSelect());
                    scor = list_data.get(position).getStr_name();
                    notifyDataSetChanged();
                }
            });
        }
    }

}
