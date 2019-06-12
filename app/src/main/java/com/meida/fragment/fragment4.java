package com.meida.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.liice.R;
import com.meida.model.LunBoTu;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PopupShareUtils;
import com.meida.utils.PreferencesUtils;
import com.meida.utils.SaveImageUtils;
import com.meida.utils.ViewBitmap;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.CacheMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISBACK;

/**
 * 作者 亢兰兰
 * 时间 2018/2/24 0024
 * 公司  郑州软盟
 */
@SuppressLint("ValidFragment")
public class fragment4 extends BaseFragment {
    int tag = 1;
    @Bind(R.id.ll_s)
    LinearLayout ll_s;
    @Bind(R.id.image_share)
    ImageView imgShare;
    @Bind(R.id.share_tit)
    TextView shareTit;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.img_erweima)
    ImageView imgErweima;
    @Bind(R.id.img_title_back)
    ImageView img_title_back;
    @Bind(R.id.img_shar)
    ImageView img_shar;

    public static fragment4 instantiation() {
        fragment4 fragment = new fragment4();
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
        View view = inflater.inflate(R.layout.fragment4, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            shareTit.setText("我是" + PreferencesUtils.getString(getActivity(), "nickname") +
                    "  " + PreferencesUtils.getString(getActivity(), "user_tel"));

        if (!TextUtils.isEmpty(PreferencesUtils.getString(getActivity(), "invite_desc_user"))) {
            tvContent.setText(PreferencesUtils.getString(getActivity(), "invite_desc_user"));
        }

        if (ISBACK == 1) {
            ISBACK = 0;
            img_title_back.setVisibility(View.VISIBLE);
            img_title_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
        }
        getlunbo();
        img_shar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ViewBitmap.getViewBitmap(ll_s);
                PopupShareUtils.showshare(getActivity(),bitmap);
            }
        });
        //文本类型
        Bitmap bitmap = new QREncode.Builder(getActivity())
                .setColor(getResources().getColor(R.color.black))//二维码颜色
                //.setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(PreferencesUtils.getString(getActivity(), "url_user"))//二维码内容
                .build().encodeAsBitmap();
        imgErweima.setImageBitmap(bitmap);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    LunBoTu lunBoTu;

    private void getlunbo() {
        mRequest = NoHttp.createStringRequest(HttpIp.bannerlist, Const.POST);
        mRequest.add("type", 3);
        mRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        mRequest.setCacheKey("logo");
        getRequest(new CustomHttpListener<LunBoTu>(getActivity(), true, LunBoTu.class) {
            @Override
            public void doWork(LunBoTu data, String code) {
                if ("1".equals(data.getCode())) {
                    lunBoTu = data;
                    if (data.getData().getList().size() > 0) {
                        if (tag >= data.getData().getList().size()) {
                            tag = 0;
                        }
                        CommonUtil.setimg(getActivity(), data.getData().getList().get(tag).getLogo(),
                                R.drawable.moren, imgShare);
                        tag++;
                    }
                }
            }
        }, true);
    }

    @OnClick({R.id.tv_change, R.id.tv_xiazai})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                if (lunBoTu.getData().getList().size() > 0) {
                    if (tag >= lunBoTu.getData().getList().size()) {
                        tag = 0;
                    }
                    CommonUtil.setimg(getActivity(), lunBoTu.getData().getList().get(tag).getLogo(),
                            R.drawable.moren, imgShare);
                    tag++;
                }
                break;
            case R.id.tv_xiazai:
                Bitmap bitmap = ViewBitmap.getViewBitmap(ll_s);
                SaveImageUtils.saveImageToGallerys(getActivity(), bitmap);
                break;
        }
    }
}
