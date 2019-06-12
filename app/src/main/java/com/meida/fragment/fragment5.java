package com.meida.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meida.MyView.CircleImageView;
import com.meida.liice.AppCaoZuoActivity;
import com.meida.liice.JiaMengActivity;
import com.meida.liice.MyAddActivity;
import com.meida.liice.R;
import com.meida.liice.RecommendUserActivity;
import com.meida.liice.RenZhengJieGuoActivity;
import com.meida.liice.SettingActivity;
import com.meida.liice.ShiMingRenZhengActivity;
import com.meida.liice.YiJianFanKuiActivity;
import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.ExchangedGoods_A;
import com.meida.ui.fg05.MyCollection_A;
import com.meida.ui.fg05.MyComment_A;
import com.meida.ui.fg05.MyIntegral_A;
import com.meida.ui.fg05.MyOrders_A;
import com.meida.ui.fg05.MyReports_A;
import com.meida.ui.fg05.PersonInfo_A;
import com.meida.ui.fg05.ServiceRecord_A;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class fragment5 extends BaseFragment {

    @Bind(R.id.img_head_fg05)
    CircleImageView imgHeadFg05;
    @Bind(R.id.tv_name_fg05)
    TextView tvNameFg05;
    @Bind(R.id.tv_tel_fg05)
    TextView tvTelFg05;
    @Bind(R.id.tv_msg1_fg05)
    TextView tvMsg1Fg05;
    @Bind(R.id.tv_msg2_fg05)
    TextView tvMsg2Fg05;
    @Bind(R.id.tv_msg3_fg05)
    TextView tvMsg3Fg05;
    @Bind(R.id.tv_msg3_fg06)
    TextView tv_msg3_fg06;
    @Bind(R.id.tv_msg4_fg05)
    TextView tvMsg4Fg05;
    /**
     * 上下文context
     */
    public Activity baseContext;


    public static fragment5 instantiation() {
        fragment5 fragment = new fragment5();
        return fragment;
    }

    //调用这个方法切换时不会释放掉Fragment
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseContext = getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (PreferencesUtils.getInt(getActivity(), "login") == 1) {
            setinfo();
            getinfo();
            Nonce.getordernum(getActivity(), new Nonce.Strings4Callback() {
                @Override
                public void doWork(String string1, String string2, String string3, String string4) {
                    if ("0".equals(string1)) {
                        tvMsg1Fg05.setVisibility(View.GONE);
                    } else {
                        tvMsg1Fg05.setVisibility(View.VISIBLE);
                        tvMsg1Fg05.setText(string1);
                    }
                    if ("0".equals(string2)) {
                        tvMsg2Fg05.setVisibility(View.GONE);
                    } else {
                        tvMsg2Fg05.setVisibility(View.VISIBLE);
                        tvMsg2Fg05.setText(string2);
                    }
                    if ("0".equals(string3)) {
                        tvMsg3Fg05.setVisibility(View.GONE);
                    } else {
                        tvMsg3Fg05.setVisibility(View.VISIBLE);
                        tvMsg3Fg05.setText(string3);
                    }
                    if ("0".equals(string4)) {
                        tv_msg3_fg06.setVisibility(View.GONE);
                    } else {
                        tv_msg3_fg06.setVisibility(View.VISIBLE);
                        tv_msg3_fg06.setText(string4);
                    }
                }
            });
            setinfo();
        }
    }

    private void setinfo() {
        CommonUtil.setcimg(getActivity(), PreferencesUtils.getString(getActivity(), "logo"),
                R.mipmap.ico_lb084, imgHeadFg05);
            tvNameFg05.setText(PreferencesUtils.getString(getActivity(), "nickname"));
//        tvTelFg05.setText(PreferencesUtils.getString(getActivity(), "user_tel"));
        String mobile = PreferencesUtils.getString(getActivity(), "user_tel");
        tvTelFg05.setText(mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.lay_top_pinfo_fg05, R.id.lay_orderall_fg05, R.id.lay_order1_fg05, R.id.lay_order2_fg05, R.id.lay_order3_fg05, R.id.lay_order4_fg05, R.id.lay_jifen_fg05, R.id.lay_baobei_fg05, R.id.lay_duihuan_fg05, R.id.lay_baoxiu_fg05, R.id.lay_shenqing_fg05, R.id.lay_renzheng_fg05, R.id.lay_shoucang_fg05, R.id.lay_dizhi_fg05, R.id.lay_guanyu_fg05, R.id.lay_caozuo_fg05, R.id.lay_fankui_fg05, R.id.lay_shezhi_fg05})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.lay_top_pinfo_fg05:
//                个人中心
                intent = new Intent(baseContext, PersonInfo_A.class);
                break;
            case R.id.lay_orderall_fg05:
                //                全部订单
                intent = new Intent(baseContext, MyOrders_A.class);
                intent.putExtra("OrderType", 0);
                break;
            case R.id.lay_order1_fg05:
                //                待付款
                intent = new Intent(baseContext, MyOrders_A.class);
                intent.putExtra("OrderType", 1);
                break;
            case R.id.lay_order2_fg05:
                //                待送货安装
                intent = new Intent(baseContext, MyOrders_A.class);
                intent.putExtra("OrderType", 2);
                break;
            case R.id.lay_order3_fg05:
                //                待评价
                intent = new Intent(baseContext, MyComment_A.class);
                intent.putExtra("CommentType", 1);
                break;
            case R.id.lay_order4_fg05:
                //                已评价
                intent = new Intent(baseContext, MyComment_A.class);
                intent.putExtra("CommentType", 2);
                break;
            case R.id.lay_jifen_fg05:
                //                我的积分
                intent = new Intent(baseContext, MyIntegral_A.class);
                break;
            case R.id.lay_baobei_fg05:
                //                报备的项目
                intent = new Intent(baseContext, MyReports_A.class);
                break;
            case R.id.lay_duihuan_fg05:
                //                已兑换商品
                intent = new Intent(baseContext, ExchangedGoods_A.class);
                break;
            case R.id.lay_baoxiu_fg05:
                //                报修记录
                intent = new Intent(baseContext, ServiceRecord_A.class);
                break;
            case R.id.lay_shenqing_fg05:
                //                申请服务商
                StartActivity(JiaMengActivity.class);
                break;
            case R.id.lay_renzheng_fg05:
                //                实名认证
                if ("2".equals(PreferencesUtils.getString(getActivity(), "card_status")) ||
                        "3".equals(PreferencesUtils.getString(getActivity(), "card_status"))) {
                    StartActivity(RenZhengJieGuoActivity.class);
                }
                if ("0".equals(PreferencesUtils.getString(getActivity(), "card_status"))) {
                    StartActivity(ShiMingRenZhengActivity.class);
                }
                if ("1".equals(PreferencesUtils.getString(getActivity(), "card_status"))) {
                    CommonUtil.showToask(getActivity(), "认证审核中");
                }
                break;
            case R.id.lay_shoucang_fg05:
                //                我的收藏
                intent = new Intent(baseContext, MyCollection_A.class);
                break;
            case R.id.lay_dizhi_fg05:
                //                收货地址
                StartActivity(MyAddActivity.class);
                break;
            case R.id.lay_guanyu_fg05:
                //               推荐用户
                StartActivity(RecommendUserActivity.class);
                break;
            case R.id.lay_caozuo_fg05:
                //                APP操作
                StartActivity(AppCaoZuoActivity.class);
                break;
            case R.id.lay_fankui_fg05:
                //                意见反馈
                startActivity(new Intent(getActivity(), YiJianFanKuiActivity.class).putExtra("tag", "1"));
                break;
            case R.id.lay_shezhi_fg05:
                //                设置
                StartActivity(SettingActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    public void getinfo() {
        mRequest = NoHttp.createStringRequest(HttpIp.info, Const.POST);
        getRequest(new CustomHttpListenermoney<Login>(getActivity(), true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                if ("1".equals(data.getCode())) {
                    Nonce.putinfo(getActivity(), data);
                }
            }
        }, false);
    }
}
