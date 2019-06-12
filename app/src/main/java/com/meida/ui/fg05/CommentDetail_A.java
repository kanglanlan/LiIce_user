package com.meida.ui.fg05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.meida.MyView.ninelayout.MsgDetailImgLstBeanM;
import com.meida.MyView.ninelayout.NineGridlayout;
import com.meida.MyView.ninelayout.NinelayoutAdapter;
import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.Goods;
import com.meida.model.PingJiaInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.photoview.demo.ImagePagerActivity;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PreferencesUtils;
import com.willy.ratingbar.ScaleRatingBar;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.MyView.ninelayout.NineGridlayout.IMG_Height;
import static com.meida.MyView.ninelayout.NineGridlayout.IMG_Width;
import static com.meida.share.Datas.ISRE;

public class CommentDetail_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.img_head_cd)
    RoundedImageView imgHeadCd;
    @Bind(R.id.tv_name_cd)
    TextView tvNameCd;
    @Bind(R.id.tv_time_cd)
    TextView tvTimeCd;
    @Bind(R.id.ratbar01_mc)
    ScaleRatingBar ratbar01Mc;
    @Bind(R.id.ratbar02_mc)
    ScaleRatingBar ratbar02Mc;
    @Bind(R.id.ratbar03_mc)
    ScaleRatingBar ratbar03Mc;
    @Bind(R.id.lay_nine_cd)
    NineGridlayout layNineCd;
    @Bind(R.id.tv_del_cd)
    TextView tvDelCd;
    @Bind(R.id.tv_note_cd)
    TextView tvNoteCd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        ButterKnife.bind(this);
        initView();
        getData(true);
    }

    private void initView() {
        changeTitle("评价详情");
    }

    PingJiaInfo pingJiaInfo;

    private void getData(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.evaluate_detail, Const.POST);
        mRequest.add("order_id", getIntent().getStringExtra("id"));
        mRequest.add("product_id", getIntent().getStringExtra("pid"));
        getRequest(new CustomHttpListener<PingJiaInfo>(baseContext, true, PingJiaInfo.class) {
            @Override
            public void doWork(PingJiaInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    pingJiaInfo = data;

                    showData(data.getData());
                }
            }
        }, true);

    }

    private void showData(final PingJiaInfo.DataBean info) {

        CommonUtil.setimg(baseContext, PreferencesUtils.getString(baseContext, "logo"), R.mipmap.ico_lb084, imgHeadCd);
        tvNameCd.setText(PreferencesUtils.getString(baseContext, "nickname"));
        tvTimeCd.setText(info.getCreate_time());
        tvNoteCd.setText(info.getContent());
        ratbar01Mc.setNumStars(Integer.parseInt(info.getLevel_manner()));
        ratbar02Mc.setNumStars(Integer.parseInt(info.getLevel_speed()));
        ratbar03Mc.setNumStars(Integer.parseInt(info.getLevel_express()));

        //订单图片
        List<String> list_imgs = new ArrayList<>(); //放 九宫格图片的
        list_imgs.clear();
        List<MsgDetailImgLstBeanM> list_imginfo = new ArrayList<>();
        for (int i = 0; i < info.getSmeta().size(); i++) {
            list_imgs.add(info.getSmeta().get(i).getUrl());
            list_imginfo.add(new MsgDetailImgLstBeanM(info.getSmeta().get(i).getUrl(), info.getSmeta().get(i).getUrl(), "0"));
        }


        NinelayoutAdapter adapter;
        layNineCd.setGap(CommonUtil.dip2px(baseContext, 10));//设置间隙
        layNineCd.setDefaultWidth(CommonUtil.dip2px(baseContext, IMG_Width));
        layNineCd.setDefaultHeight(CommonUtil.dip2px(baseContext, IMG_Height));
        int allsize = CommonUtil.getScreenWidth(baseContext);
        int singleninewidth = (allsize - CommonUtil.dip2px(baseContext, (20 * 2 + 2 * 10))) / 3;
        int allwidth = singleninewidth * 3 + CommonUtil.dip2px(baseContext, 2 * 10);
        LinearLayout.LayoutParams lp_nine = new LinearLayout.LayoutParams(allwidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_nine.setMargins(CommonUtil.dip2px(baseContext, 20), CommonUtil.dip2px(baseContext, 0), CommonUtil.dip2px(baseContext, 20), CommonUtil.dip2px(baseContext, 0));
        layNineCd.setLayoutParams(lp_nine);

        if (list_imgs.size() == 0) {
            layNineCd.setVisibility(View.GONE);
        } else {
            layNineCd.setVisibility(View.VISIBLE);
            adapter = new NinelayoutAdapter(baseContext, list_imginfo, list_imginfo.size() == 1 ? 1 : 2);
            layNineCd.setAdapter(adapter);
        }

        layNineCd.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, final int lposition) {
                String[] aStrings = new String[info.getSmeta().size()];
                for (int i = 0; i < info.getSmeta().size(); i++) {

                    aStrings[i] = info.getSmeta().get(i).getUrl();
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

    @OnClick(R.id.tv_del_cd)
    public void onViewClicked() {
        final NormalDialog dialog = new NormalDialog(baseContext);
        dialog.content("是否确定删除该评价？")
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
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

                        dialog.dismiss();
                        mRequest = NoHttp.createStringRequest(HttpIp.del_evaluate, Const.POST);
                        mRequest.add("id", pingJiaInfo.getData().getId());
                        getRequest(new CustomHttpListener<Goods>(baseContext, true, Goods.class) {
                            @Override
                            public void doWork(Goods data, String code) {
                                if ("1".equals(data.getCode())) {
                                    ISRE = 1;
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
                });


    }
}
