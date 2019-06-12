package com.meida.liice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.meida.model.AddList;
import com.meida.model.Goods;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddActivity extends BaseActivity {

    @Bind(R.id.recycle_lisst)
    RecyclerView recycleList;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    AddAdapter adapter;
    private int pager = 1;
    ArrayList<AddList.DataBean.DataBeans> datas = new ArrayList<>();
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_add);
        ButterKnife.bind(this);
        changeTitle("选择收货地址");
        initview();
        getdata(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pager = 1;
        getdata(false);
    }

    private void initview() {
        linearLayoutManager = new LinearLayoutManager(baseContext);
        recycleList.setLayoutManager(linearLayoutManager);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.main));
        recycleList.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                datas.clear();
                adapter.notifyDataSetChanged();
                pager = 1;
                getdata(false);
            }
        });
        swipeRefresh.setRefreshing(true);
        recycleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                if (lastVisibleItem >= total - 2 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        getdata(false);
                    }
                }
            }
        });
        adapter = new AddAdapter(baseContext, R.layout.item_add, datas);
        recycleList.setAdapter(adapter);
    }

    private void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.addressList, Const.POST);
        getRequest(new CustomHttpListener<AddList>(baseContext, true, AddList.class) {
            @Override
            public void doWork(AddList data, String code) {
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

    @OnClick(R.id.tv_xinjianadd)
    public void onClick() {
        startActivity(new Intent(baseContext, XinZengAddActivity.class).putExtra("tag", "1"));
    }

    public class AddAdapter extends CommonAdapter<AddList.DataBean.DataBeans> {
        private ArrayList<AddList.DataBean.DataBeans> datas = new ArrayList<AddList.DataBean.DataBeans>();
        Context mContext;

        public AddAdapter(Context context, int layoutId, ArrayList<AddList.DataBean.DataBeans> datas) {
            super(context, layoutId, datas);
            this.datas = datas;
            mContext = context;
        }

        @Override
        public void convert(final ViewHolder holder, final AddList.DataBean.DataBeans s, final int position) {
            holder.setText(R.id.tv_add_name, s.getAddress_name());
            holder.setText(R.id.tv_add_phone, s.getAddress_tel());
            holder.setText(R.id.tv_add_add, s.getArea_merger_name() + s.getAddress());
            RadioButton button = holder.getView(R.id.rb_moren);
            if ("1".equals(s.getIs_default())) {
                button.setChecked(true);
            } else {
                button.setChecked(false);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Nonce.setmoren(s.getId(), baseContext, new Nonce.StringCallback() {
                        @Override
                        public void doWork(String string) {
                            s.setIs_default("1");
                            pager=1;
                            datas.clear();
                            getdata(false);
                        }
                    });
                }
            });
            holder.getView(R.id.tv_bianji).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(baseContext, XinZengAddActivity.class)
                            .putExtra("id", s.getId())
                            .putExtra("name", s.getAddress_name())
                            .putExtra("phone", s.getAddress_tel())
                            .putExtra("add", s.getArea_merger_name())
                            .putExtra("xxadd", s.getAddress())
                            .putExtra("sid", s.getProvince_id())
                            .putExtra("shiid", s.getCity_id())
                            .putExtra("xianid", s.getArea_id())
                            .putExtra("is", s.getIs_default())
                            .putExtra("tag","2")
                    );
                }
            });
            holder.getView(R.id.tv_shanchu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(getIntent().getStringExtra("tag"))) {
                        Intent intent = new Intent();
                        intent.putExtra("id", s.getId());
                        intent.putExtra("name", s.getAddress_name());
                        intent.putExtra("tel", s.getAddress_tel());
                        intent.putExtra("add", s.getArea_merger_name() + s.getAddress());
                        intent.putExtra("shengid", s.getProvince_id());
                        intent.putExtra("shiid", s.getCity_id());
                        intent.putExtra("xianid", s.getArea_id());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
    }

    public void delete(final int po) {
        mRequest = NoHttp.createStringRequest(HttpIp.delAddress, Const.POST);
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
        }, true);
    }


}
