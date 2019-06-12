package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.meida.model.Cardlist;
import com.meida.model.FenLeiList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YinHangKaActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_hang_ka);
        ButterKnife.bind(this);
        changeTitle("银行列表");
        getlist();

    }

    public void getlist() {
        Nonce.getpinpai(true, 6, baseContext, new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                listview.setAdapter(new CommonAdapter<FenLeiList.DataBean.ListBean>(baseContext,
                        R.layout.item_yinhang, data.getData().getList()) {
                    @Override
                    protected void convert(ViewHolder viewHolder, final FenLeiList.DataBean.ListBean item, int position) {
                        viewHolder.setText(R.id.tv_name, item.getTitle());
                        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.putExtra("id", item.getId());
                                intent.putExtra("name", item.getTitle());
                                intent.putExtra("card_logo", item.getLogo());
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });
                    }
                });
            }
        });
        mRequest = NoHttp.createStringRequest(HttpIp.getBankCard, Const.POST);
        getRequest(new CustomHttpListener<Cardlist>(baseContext, true, Cardlist.class) {
            @Override
            public void doWork(Cardlist data, String code) {
                if ("1".equals(data.getCode())) {

                }
            }
        }, true);
    }
}
