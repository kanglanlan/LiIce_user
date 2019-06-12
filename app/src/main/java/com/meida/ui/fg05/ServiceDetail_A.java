package com.meida.ui.fg05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.MyView.ninelayout.MsgDetailImgLstBeanM;
import com.meida.MyView.ninelayout.NineGridlayout;
import com.meida.MyView.ninelayout.NinelayoutAdapter;
import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.WeiXiuList;
import com.meida.photoview.demo.ImagePagerActivity;
import com.meida.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.MyView.ninelayout.NineGridlayout.IMG_Height;
import static com.meida.MyView.ninelayout.NineGridlayout.IMG_Width;

/**
 * 维修记录详情
 *
 * @author Administrator-LFC
 *         created at 2018/8/16 10:19
 */
public class ServiceDetail_A extends BaseActivity {

    @Bind(R.id.tv_state_sd)
    TextView tvStateSd;
    @Bind(R.id.tv_orderno_sd)
    TextView tvOrdernoSd;
    @Bind(R.id.tv_machinetype_sd)
    TextView tvMachinetypeSd;
    @Bind(R.id.tv_adstype_sd)
    TextView tvAdstypeSd;
    @Bind(R.id.tv_servicetime_sd)
    TextView tvServicetimeSd;
    @Bind(R.id.tv_ads_sd)
    TextView tvAdsSd;
    @Bind(R.id.tv_ownername_sd)
    TextView tvOwnernameSd;
    @Bind(R.id.tv_ownertel_sd)
    TextView tvOwnertelSd;
    @Bind(R.id.tv_applytime_sd)
    TextView tvApplytimeSd;
    @Bind(R.id.tv_note_sd)
    TextView tvNoteSd;
    @Bind(R.id.lay_nine1_sd)
    NineGridlayout layNine1Sd;
    @Bind(R.id.tv_servicename_sd)
    TextView tvServicenameSd;
    @Bind(R.id.tv_reason_sd)
    TextView tvReasonSd;
    @Bind(R.id.lay_nine2_sd)
    NineGridlayout layNine2Sd;
    @Bind(R.id.lay_service_sd)
    LinearLayout layServiceSd;
    @Bind(R.id.tv_reward_sd)
    TextView tvRewardSd;
    @Bind(R.id.tv_pingjia_sd)
    TextView tvPingjiaSd;
    @Bind(R.id.lay_bottom_sd)
    LinearLayout layBottomSd;
    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    WeiXiuList.DataBean.DataBsean info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        changeTitle("维修记录详情");
        info = (WeiXiuList.DataBean.DataBsean) getIntent().getSerializableExtra("info");
        showData();
    }
    private void showData() {
//        订单状态
        if ("3".equals(info.getStatus())) {
            tvStateSd.setText("已完成");
            tvStateSd.setTextColor(getResources().getColor(R.color.txthui));
// 已完成才有维修意见  和底部 评价操作
            layServiceSd.setVisibility(View.VISIBLE);
            layBottomSd.setVisibility(View.VISIBLE);
            if("2".equals(info.getComment_status())){
                tvPingjiaSd.setVisibility(View.GONE);
            }

        } else {
            tvStateSd.setText("待维修");
            tvStateSd.setTextColor(getResources().getColor(R.color.org));
            layServiceSd.setVisibility(View.GONE);
            layBottomSd.setVisibility(View.GONE);
        }
        tvOrdernoSd.setText(info.getOrder_id());//订单编号
        tvMachinetypeSd.setText(info.getProduct_info().getProduct_name());//维修机型
        tvAdstypeSd.setText(info.getProduct_info().getProduct_spec());//类型
        tvServicetimeSd.setText(info.getAddress_service_time());//上门时间
        tvAdsSd.setText(info.getArea_merger_name()+info.getAddress());//地址
        tvOwnernameSd.setText(info.getAddress_name());//报修人
        tvOwnertelSd.setText(info.getAddress_tel());//报修电话
        tvApplytimeSd.setText(info.getCreate_time());//申请时间
        tvNoteSd.setText(info.getUser_content());//备注
        tvServicenameSd.setText(info.getMerchant_info().getNickname());//维修师傅
        tvReasonSd.setText(info.getMerchant_content());//维修原因

//订单图片

        List<String> list_imgs = new ArrayList<>(); //放 九宫格图片的
        list_imgs.clear();
        List<MsgDetailImgLstBeanM> list_imginfo = new ArrayList<>();
        for (int i = 0; i < info.getUser_smeta().size(); i++) {
            list_imgs.add(info.getUser_smeta().get(i).getUrl());
//                            原图 缩略图啥的  随缘吧 QAQ
            list_imginfo.add(new MsgDetailImgLstBeanM(info.getUser_smeta().get(i).getUrl(),
                    info.getUser_smeta().get(i).getUrl(), "0"));
        }


        NinelayoutAdapter adapter;
        layNine1Sd.setGap(CommonUtil.dip2px(baseContext, 10));//设置间隙
        layNine1Sd.setDefaultWidth(CommonUtil.dip2px(baseContext, IMG_Width));
        layNine1Sd.setDefaultHeight(CommonUtil.dip2px(baseContext, IMG_Height));
        int allsize = CommonUtil.getScreenWidth(baseContext);
        int singleninewidth = (allsize - CommonUtil.dip2px(baseContext, (18 * 2 + 2 * 10))) / 3;
        int allwidth = singleninewidth * 3 + CommonUtil.dip2px(baseContext, 2 * 10);
        LinearLayout.LayoutParams lp_nine = new LinearLayout.LayoutParams(allwidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_nine.setMargins(CommonUtil.dip2px(baseContext, 0), CommonUtil.dip2px(baseContext, 15), CommonUtil.dip2px(baseContext, 0), CommonUtil.dip2px(baseContext, 0));
        layNine1Sd.setLayoutParams(lp_nine);

        if (list_imgs.size() == 0) {
            layNine1Sd.setVisibility(View.GONE);
        } else {
            layNine1Sd.setVisibility(View.VISIBLE);
            adapter = new NinelayoutAdapter(baseContext, list_imginfo, list_imginfo.size() == 1 ? 1 : 2);
            layNine1Sd.setAdapter(adapter);
        }

        layNine1Sd.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, final int lposition) {

                String[] aStrings = new String[info.getUser_smeta().size()];
                for (int i = 0; i < info.getUser_smeta().size(); i++) {

                    aStrings[i] = info.getUser_smeta().get(i).getUrl();
                }
                Intent intent = new Intent(baseContext,
                        ImagePagerActivity.class);
                // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                        aStrings);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,
                        lposition);
                startActivity(intent);

            }
        });
//        维修图片
        List<String> list_imgs2 = new ArrayList<>(); //放 九宫格图片的
        list_imgs2.clear();
        List<MsgDetailImgLstBeanM> list_imginfo2 = new ArrayList<>();
        for (int i = 0; i <info.getMerchant_smeta().size(); i++) {
            list_imgs2.add(info.getMerchant_smeta().get(i).getUrl());
//                            原图 缩略图啥的  随缘吧 QAQ
            list_imginfo2.add(new MsgDetailImgLstBeanM(info.getMerchant_smeta().get(i).getUrl()
                    , info.getMerchant_smeta().get(i).getUrl(), "0"));
        }


        NinelayoutAdapter adapter2;
        layNine2Sd.setGap(CommonUtil.dip2px(baseContext, 10));//设置间隙
        layNine2Sd.setDefaultWidth(CommonUtil.dip2px(baseContext, IMG_Width));
        layNine2Sd.setDefaultHeight(CommonUtil.dip2px(baseContext, IMG_Height));

        LinearLayout.LayoutParams lp_nine2 = new LinearLayout.LayoutParams(allwidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_nine2.setMargins(CommonUtil.dip2px(baseContext, 0), CommonUtil.dip2px(baseContext, 15), CommonUtil.dip2px(baseContext, 0), CommonUtil.dip2px(baseContext, 0));
        layNine2Sd.setLayoutParams(lp_nine2);

        if (list_imgs2.size() == 0) {
            layNine2Sd.setVisibility(View.GONE);
        } else {
            layNine2Sd.setVisibility(View.VISIBLE);
            adapter = new NinelayoutAdapter(baseContext, list_imginfo2, list_imginfo2.size() == 1 ? 1 : 2);
            layNine2Sd.setAdapter(adapter);
        }

        layNine2Sd.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, final int lposition) {
               String[] aStrings = new String[info.getMerchant_smeta().size()];
                for (int i = 0; i < info.getMerchant_smeta().size(); i++) {

                    aStrings[i] = info.getMerchant_smeta().get(i).getUrl();
                }
                Intent intent = new Intent(baseContext,
                        ImagePagerActivity.class);
                // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                        aStrings);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,
                        lposition);
                startActivity(intent);

            }
        });

    }

    @OnClick({R.id.tv_reward_sd, R.id.tv_pingjia_sd})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_reward_sd:
//                CommonUtil.showToask(baseContext, "积分打赏");
                intent = new Intent(baseContext, IntegralReward_A.class);
                intent.putExtra("type","2");
                intent.putExtra("id",info.getOrder_id());
                break;
            case R.id.tv_pingjia_sd:
                intent = new Intent(baseContext, WaitComment_A.class);
                intent.putExtra("type","3");
                intent.putExtra("id",info.getOrder_id());
                intent.putExtra("pid",info.getProduct_id());
                intent.putExtra("transaction_number",info.getTransaction_number());
                break;
        }
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}
