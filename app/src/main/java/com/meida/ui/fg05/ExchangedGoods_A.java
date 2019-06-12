package com.meida.ui.fg05;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.ExchangedGoodsInfo_A;
import com.meida.liice.R;
import com.meida.model.Orderlist;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.meida.share.Datas.ISRE;

/**
 * 已兑换的商品
 *
 * @author Administrator-LFC
 *         created at 2018/8/16 17:00
 */
public class ExchangedGoods_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.recycle_lisst)
    RecyclerView recycleLisst;
    @Bind(R.id.empty_img)
    ImageView emptyImg;
    @Bind(R.id.empty_hint)
    TextView emptyHint;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private Adapter_EXGoods adapter;
    private List<Orderlist.DataBean.DataBeasn> datas = new ArrayList<>();
    private boolean isLoadingMore;
    private int pager = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchanged_goods);
        ButterKnife.bind(this);
        initView();
        getData(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ISRE == 1) {
            ISRE = 0;
            pager = 1;
            getData(false);
        }
    }

    private void initView() {

        changeTitle("已兑换的商品");
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_EXGoods(baseContext, R.layout.item_exgoods, datas);
        recycleLisst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                pager = 1;
                getData(false);
            }
        });
        recycleLisst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                Log.e("--lfc", "lastVisibleItem: " + lastVisibleItem + " total-3 : " + (total - 3) + "  dy:" + dy);
                if (lastVisibleItem >= total - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getData(false);

                    }
                }

            }
        });

    }

    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.index, Const.POST);
        mRequest.add("order_type", "2");    //1商品2兑换商品
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<Orderlist>(baseContext, true, Orderlist.class) {
            @Override
            public void doWork(Orderlist data, String code) {
                if ("1".equals(data.getCode())) {
                    if (pager == 1) {
                        datas.clear();
                    }
                    datas.addAll(data.getData().getData());
                    adapter.notifyDataSetChanged();
                    pager++;
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                isLoadingMore = false;
                if (datas.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }


    /**
     * 已兑换的商品
     */
    public class Adapter_EXGoods extends CommonAdapter<Orderlist.DataBean.DataBeasn> {

        public Adapter_EXGoods(Context context, int layoutId, List<Orderlist.DataBean.DataBeasn> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final Orderlist.DataBean.DataBeasn s, final int position) {
            holder.setText(R.id.tv_orderno_itemeg, "订单编号：" + s.getOrder_id());//订单号
            holder.setText(R.id.tv_state_itemeg, s.getStatus_name());//发货状态
//            商品图片
            CommonUtil.setimg(baseContext, s.getList().get(0).getProduct_logo(), R.drawable.moren, (ImageView) holder.getView(R.id.img_head_itemeg));
            holder.setText(R.id.tv_gname_itemeg, s.getList().get(0).getProduct_name());//商品名称
            holder.setText(R.id.tv_integral_itemeg, "积分：" + s.getOrder_amount() + "积分");//兑换积分
            holder.setText(R.id.tv_count_itemeg, "x1");//商品数量
            TextView tv_hasten = holder.getView(R.id.tv_hasten_itemeg);//提醒发货
            TextView tv_get = holder.getView(R.id.tv_get_itemeg);//确认收货
            //富文本显示
            String name1 = "共1件商品    实付款：";
            String name2 = s.getOrder_amount();
            String name3 = "积分";

            String str = name1 + name2 + name3;
            final SpannableStringBuilder sp = new SpannableStringBuilder(str);
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.black)), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(13, true), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.org)), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(13, true), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.black)), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(13, true), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            //        这个 SpannableStringBuilder 如果转为 tostring 就没有作用了！
            TextView tv_note = holder.getView(R.id.tv_note_itemeg);//商品备注
            tv_note.setText(sp);
//状态0或空为全部，1已取消2待支付3待送装4待确认5已完成
            if (!"4".equals(s.getStatus())) {
                tv_hasten.setVisibility(View.VISIBLE);
                tv_get.setVisibility(View.GONE);

            } else {
                tv_hasten.setVisibility(View.GONE);
                tv_get.setVisibility(View.VISIBLE);
            }
            if ("5".equals(s.getStatus())) {
                tv_hasten.setVisibility(View.GONE);
                tv_get.setVisibility(View.GONE);
            }
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!"4".equals(s.getStatus())) {
                        startActivity(new Intent(baseContext, ExchangedGoodsInfo_A.class)
                                .putExtra("id", s.getOrder_id())
                                .putExtra("title", "待发货")
                        );
                    } else {
                        startActivity(new Intent(baseContext, ExchangedGoodsInfo_A.class)
                                .putExtra("id", s.getOrder_id())
                                .putExtra("title", "已发货")
                        );

                    }

                }
            });
            tv_hasten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Nonce.order("", s.getOrder_id(), "3", baseContext, new Nonce.StringCallback() {
                        @Override
                        public void doWork(String string) {

                        }
                    });
                }
            });
            tv_get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Nonce.order("", s.getOrder_id(), "4", baseContext, new Nonce.StringCallback() {
                        @Override
                        public void doWork(String string) {
                            s.setStatus("5");
                            s.setStatus_name("已完成");
                            notifyDataSetChanged();
                        }
                    });

                }
            });

        }
    }

}
