package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.meida.model.Cardlist;
import com.meida.model.Goods;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BianJiYinHangKaActivity extends BaseActivity {
    @Bind(R.id.listview)
    ListView listview;
    CommonAdapter adapter;
    ArrayList<Cardlist.DataBean.DataBesan> datats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_ji_yin_hang_ka);
        ButterKnife.bind(this);
        changeTitle("我的银行卡");
        init();
        getata();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getata();
    }

    private void init() {
        adapter = new CommonAdapter<Cardlist.DataBean.DataBesan>(baseContext,
                R.layout.item_yinhangka, datats) {
            @Override
            protected void convert(ViewHolder viewHolder, final Cardlist.DataBean.DataBesan item, final int position) {
                ImageView img = viewHolder.getView(R.id.yinghangka);
                viewHolder.getView(R.id.ll_binaji).setVisibility(View.VISIBLE);
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
                viewHolder.getView(R.id.bianji).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//编辑
                        startActivity(new Intent(baseContext,TianJiaYinHangKaActivity.class)
                                .putExtra("id",item.getId())
                                .putExtra("name",item.getCard_name())
                                .putExtra("num",item.getCard_number())
                                .putExtra("cardid",item.getBank_id())
                                .putExtra("bankname",item.getBank_name())
                                .putExtra("subname",item.getSub_bank_name())
                                .putExtra("logo",item.getLogo())
                        );
                    }
                });
                viewHolder.getView(R.id.tv_shanchu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//删除
                        final NormalDialog dialog = new NormalDialog(baseContext);
                        dialog.content("是否确定删除银行卡？")
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
                                        delete(position);
                                    }
                                });
                    }
                });         }
        };
        listview.setAdapter(adapter);
    }

    public void delete(final int po) {
        mRequest = NoHttp.createStringRequest(HttpIp.delBankCard, Const.POST);
        mRequest.add("ids", datats.get(po).getId());
        getRequest(new CustomHttpListener<Goods>(baseContext, true, Goods.class) {
            @Override
            public void doWork(Goods data, String code) {
                if ("1".equals(data.getCode())) {
                    datats.remove(po);
                    adapter.notifyDataSetChanged();
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


    public void getata() {
        mRequest = NoHttp.createStringRequest(HttpIp.bankCardList, Const.POST);
        mRequest.add("per_page", 50);
        getRequest(new CustomHttpListener<Cardlist>(baseContext, true, Cardlist.class) {
            @Override
            public void doWork(Cardlist data, String code) {
                if ("1".equals(data.getCode())) {
                    datats.clear();
                    datats.addAll(data.getData().getData());
                    adapter.notifyDataSetChanged();
                }
            }
        }, true);
    }
}
