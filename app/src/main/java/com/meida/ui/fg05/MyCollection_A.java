package com.meida.ui.fg05;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.meida.liice.BaseActivity;
import com.meida.liice.BuTieInfoActivity;
import com.meida.liice.R;
import com.meida.model.Goods;
import com.meida.model.ShouCangList;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的收藏
 * <p>
 * 还缺少一个 我是有底线的哦~ 底部的view
 *
 * @author Administrator-LFC
 *         created at 2018/8/17 10:37
 */
public class MyCollection_A extends BaseActivity {

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

    ArrayList<ShouCangList.DataBean.DataBesan> datas = new ArrayList<>();
    private Adapter_Collection adapter;
    private boolean isLoadingMore;
    private int pager = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        initView();
        swipeRefresh.setRefreshing(true);
        getData(true);
    }

    private void initView() {
        changeTitle("我的收藏");
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleLisst.setLayoutManager(linearLayoutManager);
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
                Log.e("--lfc", "lastVisibleItem: " + lastVisibleItem + " total-3 : " + (total) + "  dy:" + dy);
                if (lastVisibleItem >= total - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getData(false);

                    }
                }

            }
        });
        adapter = new Adapter_Collection(baseContext, R.layout.item_collect, datas);
        recycleLisst.setAdapter(adapter);
    }

    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.myCollect, Const.POST);
        mRequest.add("type", "1");    //类型不能为空1收藏2浏览
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<ShouCangList>(baseContext, true, ShouCangList.class) {
            @Override
            public void doWork(ShouCangList data, String code) {
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
                if(datas.size()==0)
                {
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }


    /**
     * 赚积分
     */
    public class Adapter_Collection extends CommonAdapter<ShouCangList.DataBean.DataBesan> {

        public Adapter_Collection(Context context, int layoutId, List<ShouCangList.DataBean.DataBesan> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final ShouCangList.DataBean.DataBesan s, final int position) {
            LinearLayout line = holder.getView(R.id.line_itemc);
            line.setVisibility((position == datas.size() - 1) ? View.GONE : View.VISIBLE);
            //图片
            ImageView img_info = holder.getView(R.id.img_itemc);
            CommonUtil.setimg(baseContext, s.getLogo(), R.drawable.moren, img_info);
            holder.setText(R.id.tv_title_itemc, s.getTitle());
            holder.setText(R.id.tv_note_itemc, s.getContent());
            holder.setText(R.id.tv_money_itemc, s.getUser_price());
            LinearLayout lay_tags = holder.getView(R.id.lay_tags_itemc);
            lay_tags.removeAllViews();
            if (s.getLabels().size() > 0) {
                for (int i = 0; i < s.getLabels().size(); i++) {
                    View view = View.inflate(mContext, R.layout.item_biaoqian, null);
                    TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                    textView.setText(s.getLabels().get(i));
                    lay_tags.addView(view);
                }
            }
            holder.getView(R.id.lay_del_itemc).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final NormalDialog dialog = new NormalDialog(baseContext);
                    dialog.content("是否确定删除收藏内容？")
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
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, BuTieInfoActivity.class).putExtra("id", s.getSource_id()));

                }
            });
        }
    }


    /**
     * user/delcollect删除收藏
     */
    private void delete(final int po) {
        mRequest = NoHttp.createStringRequest(HttpIp.delcollect, Const.POST);
        mRequest.add("type", "1");    //1商品2兑换商品
        mRequest.add("ids", datas.get(po).getId());
        getRequest(new CustomHttpListener<Goods>(baseContext, true, Goods.class) {
            @Override
            public void doWork(Goods data, String code) {
                if ("1".equals(data.getCode())) {
                    datas.remove(po);
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
        }, false);
    }


}
