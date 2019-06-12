package com.meida.ui.fg05.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.meida.MyView.MyListView;
import com.meida.fragment.BaseFragment;
import com.meida.liice.OrderInfoActivity;
import com.meida.liice.PayActivity;
import com.meida.liice.R;
import com.meida.model.FenLeiList;
import com.meida.model.Orderlist;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.Nonce;
import com.meida.wheelview.WheelView;
import com.meida.wheelview.adapter.ArrayWheelAdapter;
import com.yolanda.nohttp.NoHttp;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.meida.share.Datas.ISRE;

@SuppressLint("ValidFragment")
public class FG_Orders extends BaseFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    //     0全部 1 待付款 2 待送货安装 3 待评价  4 已评价
    private int mParam1;
    private String mParam2;


    private Adapter_Order adapter;
    private List<Orderlist.DataBean.DataBeasn> datas = new ArrayList<>();
    private boolean isLoadingMore;
    private int pager = 1;
    private FragmentActivity baseContext;
    private BottomBaseDialog dialog;

    public FG_Orders() {
        // Required empty public constructor
    }


    public static FG_Orders newInstance(int param1, String param2) {
        FG_Orders fragment = new FG_Orders();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ISRE == 1) {
            ISRE = 0;
            pager = 1;
            datas.clear();
            swipeRefresh.setRefreshing(true);
            getData(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_orders, container, false);
        ButterKnife.bind(this, view);
        baseContext = getActivity();
        return view;
    }

    String[] quxdatas;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getData(true);
        Nonce.getpinpai(false, 7, getActivity(), new Nonce.fenleiCallback() {
            @Override
            public void doWork(FenLeiList data) {
                quxdatas = new String[data.getData().getList().size()];
                for (int i = 0; i < data.getData().getList().size(); i++) {
                    quxdatas[i] = data.getData().getList().get(i).getTitle();
                }
            }
        });
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_Order(baseContext, R.layout.item_orders, datas);
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
                        pageNum++;
                        getData(false);

                    }
                }

            }
        });

    }

    private void getData(boolean isload) {
        mRequest = NoHttp.createStringRequest(HttpIp.index, Const.POST);
        mRequest.add("order_type", "1");    //1商品2兑换商品
        mRequest.add("status", mParam1);
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
                    adapter.notifyDataSetChanged();
                    recycleLisst.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    recycleLisst.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            }
        }, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 报备的项目
     */
    public class Adapter_Order extends CommonAdapter<Orderlist.DataBean.DataBeasn> {

        public Adapter_Order(Context context, int layoutId, List<Orderlist.DataBean.DataBeasn> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final Orderlist.DataBean.DataBeasn s, final int position) {
//            按钮事件
            MyListView listview = holder.getView(R.id.listview);//取消订单
            listview.setAdapter(new com.zhy.adapter.abslistview.CommonAdapter<Orderlist.DataBean.DataBeasn.ListBean>(
                    baseContext, R.layout.item_ziorder, s.getList()
            ) {
                @Override
                protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, Orderlist.DataBean.DataBeasn.ListBean item, int position) {
                    ImageView img = viewHolder.getView(R.id.img_itemo);
                    CommonUtil.setimg(baseContext, item.getProduct_logo(), R.drawable.moren, img);
                    viewHolder.setText(R.id.tv_title_itemo, item.getProduct_name());
                    viewHolder.setText(R.id.tv_note_itemo, item.getProduct_spec());
                    viewHolder.setText(R.id.tv_money_itemo, item.getProduct_price());
                    viewHolder.setText(R.id.tv_count_itemo, "x" + item.getProduct_num());
                    viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(baseContext, OrderInfoActivity.class).putExtra("id", s.getOrder_id()));

                        }
                    });
                }
            });
            TextView tv_cancle = holder.getView(R.id.tv_cancle_itemo);//取消订单
            TextView tv_pay = holder.getView(R.id.tv_pay_itemo);//付款
            TextView tv_over = holder.getView(R.id.tv_over_itemo);//确认安装完成
            TextView tv_tip = holder.getView(R.id.tv_tip_itemo);//积分打赏
            TextView tv_pingjia = holder.getView(R.id.tv_pingjia_itemo);//去评价
            holder.setText(R.id.tv_orderno_itemo, "订单号：" + s.getOrder_id());//订单编号
            String str_type = "";

            switch (s.getStatus()) {//状态0或空为全部，1已取消2待支付3待送装4待确认5已完成
                case "2":
                    str_type = "待付款";
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_pay.setVisibility(View.VISIBLE);
                    tv_over.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.GONE);
                    tv_pingjia.setVisibility(View.GONE);
                    break;
                case "3":
                    str_type = "待送货安装";
                    tv_cancle.setVisibility(View.GONE);
                    tv_pay.setVisibility(View.GONE);
                    tv_over.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.GONE);
                    tv_pingjia.setVisibility(View.GONE);
                    break;
                case "4":
                    str_type = "待确认";
                    tv_cancle.setVisibility(View.GONE);
                    tv_pay.setVisibility(View.GONE);
                    tv_over.setVisibility(View.VISIBLE);
                    // TODO: 2018/8/18  这个地方可能要 有判断  是否已经打赏过了
                    tv_tip.setVisibility(View.GONE);
                    tv_pingjia.setVisibility(View.GONE);
                    break;
                default:
                    tv_cancle.setVisibility(View.GONE);
                    tv_pay.setVisibility(View.GONE);
                    tv_over.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.GONE);
                    tv_pingjia.setVisibility(View.GONE);
                    break;
            }
            holder.setText(R.id.tv_state_itemo, s.getStatus_name());//订单状态
            //富文本显示
            String name1 = "共" + s.getTotal_product_num() + "件商品,实付：";
            String name2 = "￥ " + s.getPay_amount();
            String name3 = "";
            String str = name1 + name2 + name3;
            final SpannableStringBuilder sp = new SpannableStringBuilder(str);
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.txthui2)), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.org)), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), name1.length(), (name1 + name2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            sp.setSpan(new ForegroundColorSpan(baseContext.getResources().getColor(R.color.txthui2)), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
            sp.setSpan(new AbsoluteSizeSpan(14, true), (name1 + name2).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
            //        这个 SpannableStringBuilder 如果转为 tostring 就没有作用了！
            TextView tv_info = holder.getView(R.id.tv_info_itemo);//商品备注
            tv_info.setText(sp);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(baseContext, OrderInfoActivity.class).putExtra("id", s.getOrder_id()));
                }
            });
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final NormalDialog dialog = new NormalDialog(getActivity());
                    dialog.content("确认取消订单？")//
                            .style(NormalDialog.STYLE_TWO)//
                            .titleLineColor(getResources().getColor(R.color.black))
                            .title("提示")
                            .titleTextSize(23)//
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
                                    Log.i("=======", "点击确定");
                                    dialog.dismiss();
                                    if (quxdatas != null) {
                                        showreson(quxdatas, s.getOrder_id(), position);
                                    } else {
                                        Nonce.getpinpai(true, 7, getActivity(), new Nonce.fenleiCallback() {
                                            @Override
                                            public void doWork(FenLeiList data) {
                                                quxdatas = new String[data.getData().getList().size()];
                                                for (int i = 0; i < data.getData().getList().size(); i++) {
                                                    quxdatas[i] = data.getData().getList().get(i).getTitle();
                                                }
                                                showreson(quxdatas, s.getOrder_id(), position);
                                            }
                                        });
                                    }
                                }
                            });

                }
            });
            tv_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(baseContext, PayActivity.class)
                            .putExtra("id", s.getOrder_id())
                            .putExtra("qian", s.getPay_amount())
                    );

                }
            });
            tv_over.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final NormalDialog dialog = new NormalDialog(getActivity());
                    dialog.content("确认安装完成？")//
                            .style(NormalDialog.STYLE_TWO)//
                            .titleLineColor(getResources().getColor(R.color.black))
                            .title("提示")
                            .titleTextSize(23)//
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
                                    Log.i("=======", "点击确定");
                                    dialog.dismiss();
                                    Nonce.order("", s.getOrder_id(), "4", getActivity(), new Nonce.StringCallback() {
                                        @Override
                                        public void doWork(String string) {
                                            pager = 1;
                                            datas.clear();
                                            swipeRefresh.setRefreshing(true);
                                            getData(false);
                                        }
                                    });
                                }
                            });
                }
            });
        }
    }


    public void showreson(final String[] string, final String id, final int position) {
        final View view = View.inflate(getActivity(), R.layout.popu_one, null);
        final WheelView wv1 = (WheelView) view.findViewById(R.id.wl_1);
        TextView title = (TextView) view.findViewById(R.id.tv_popu_title);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        TextView tv_queding = (TextView) view.findViewById(R.id.tv_queding);
        title.setText("取消订单");
        wv1.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), string));
        dialog = new BottomBaseDialog(getActivity()) {
            @Override
            public View onCreateView() {
                return view;
            }

            @Override
            public void setUiBeforShow() {
            }
        };
        dialog.show();
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Nonce.order(string[wv1.getCurrentItem()], id, "1", getActivity(), new Nonce.StringCallback() {
                    @Override
                    public void doWork(String string) {
                        datas.get(position).setStatus_name("已关闭");
                        datas.get(position).setStatus("1");
                        pager=1;
                        datas.clear();
                        swipeRefresh.setRefreshing(true);
                        getData(false);
                    }
                });
            }
        });
    }

}
