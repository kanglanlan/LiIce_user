package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meida.model.Cardlist;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.meida.liice.R.id.yinghangka;

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
        getata(true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
            getata(false);
    }

    @OnClick(R.id.tv_zhuaneng)
    public void onClick() {
        StartActivity(TianJiaYinHangKaActivity.class);
    }

    public void getata(boolean b) {
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
                            ImageView img = viewHolder.getView(yinghangka);
                            CommonUtil.setimg(baseContext, item.getLogo(), R.drawable.moren, img);
                            viewHolder.setText(R.id.tv_name, item.getBank_name());
                            viewHolder.setText(R.id.tv_kahao, item.getCard_number());

                            viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mRequest = NoHttp.createStringRequest(HttpIp.bank_card_last_use, Const.POST);
                                    mRequest.add("id",  item.getId());
                                    getRequest(new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
                                        @Override
                                        public void doWork(Coommt data, String code) {
                                            if ("1".equals(data.getCode())) {
                                              finish();
                                            }
                                        }
                                    }, false);
//                                    Intent intent = new Intent();
//                                    intent.putExtra("id", item.getId());
//                                    intent.putExtra("name", item.getBank_name());
//                                    intent.putExtra("num", item.getCard_number());
//                                    intent.putExtra("card_logo", item.getCard_logo());
//                                    setResult(RESULT_OK, intent);
//                                    finish();
                                }
                            });
                        }
                    });
                }
            }
        }, b);
    }
}
