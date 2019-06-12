package com.meida.ui.fg05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.BianJiYinHangKaActivity;
import com.meida.liice.R;
import com.meida.liice.TianJiaYinHangKaActivity;
import com.meida.model.Cardlist;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.share.Datas.ISRE;

public class MyYinHangKaActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yin_hang_ka);
        ButterKnife.bind(this);
        changeTitle("我的银行卡");
        tvTitleRight.setText("编辑");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity(BianJiYinHangKaActivity.class);
            }
        });
        getata();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ISRE == 1) {
            ISRE = 0;
            getata();
        }
    }

    @OnClick(R.id.tv_zhuaneng)
    public void onClick() {
        StartActivity(TianJiaYinHangKaActivity.class);
    }

    public void getata() {
        mRequest = NoHttp.createStringRequest(HttpIp.bankCardList, Const.POST);
        mRequest.add("per_page", 50);
        getRequest(new CustomHttpListener<Cardlist>(baseContext, true, Cardlist.class) {
            @Override
            public void doWork(Cardlist data, String code) {
                if ("1".equals(data.getCode())) {
                    listview.setAdapter(new CommonAdapter<Cardlist.DataBean.DataBesan>(baseContext,
                            R.layout.item_yinhangka, data.getData().getData()) {
                        @Override
                        protected void convert(ViewHolder viewHolder, final Cardlist.DataBean.DataBesan item, int position) {
                            ImageView img = viewHolder.getView(R.id.yinghangka);
                            CommonUtil.setimg(baseContext, item.getLogo(), R.drawable.moren, img);
                            viewHolder.setText(R.id.tv_name, item.getBank_name());
                            viewHolder.setText(R.id.tv_kahao, item.getCard_number());

                            viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent();
                                    intent.putExtra("id", item.getId());
                                    intent.putExtra("name", item.getBank_name());
                                    intent.putExtra("num", item.getCard_number());
                                    intent.putExtra("card_logo", item.getCard_logo());
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            }
        }, true);
    }
}
