package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.MyView.CustomGridView;
import com.meida.model.RecordList;
import com.meida.model.XiangMuInfo;
import com.meida.nohttp.CustomHttpListener;
import com.meida.photoview.demo.ImagePagerActivity;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.ui.fg05.RepayPlan_A;
import com.meida.utils.CommonUtil;
import com.wrbug.timeline.TimeLineView;
import com.yolanda.nohttp.NoHttp;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimAdapterEx;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReportsDetail_Activity extends BaseActivity {
    @Bind(R.id.recycle_lisst)
    RecyclerView recycleLisst;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.bt_xiugaijindu)
    Button btXiugaijindu;
    private int pager = 1;
    ArrayList<RecordList.DataBean.DataBeans> datas = new ArrayList<>();
    TimeLineView timeLineView;
    TextView huankuan, tvtime, tv_status, tv_jilu, tvbianhao, tvname, tvleixing, tvminaji, tvyezhuname, tvyezhuphone,
            tvtuijianren, tvyixiangpinpai, tvyixiangkongtiao, tvpayment, tvadd, tvcontent;
    /**
     * 是否正在上拉加载中
     */
    public boolean isLoadingMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_detail_);
        ButterKnife.bind(this);
        changeTitle("报备的项目");
        initview();
        getdata(true);
    }


    private void initview() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.main));
        linearLayoutManager = new LinearLayoutManager(this);
        recycleLisst.setLayoutManager(linearLayoutManager);
        recycleLisst.setFocusableInTouchMode(false);
        View view = View.inflate(this, R.layout.info_baobeitop, null);
        timeLineView = (TimeLineView) view.findViewById(R.id.timeLineView1);
        String[] txt = new String[]{"已接洽", "已提交方案", "已签订合同", "已付款", "已验收", "已施工"};
//        状态（1已关闭；2已报备；3已接洽；4已提交方案；5已签合同；6已付款；7已施工；8已验收；）
        timeLineView.builder().pointStrings(txt, 1).load();

        tv_status = (TextView) view.findViewById(R.id.tv_status);
        huankuan = (TextView) view.findViewById(R.id.tv_huankuan);
        tvtime = (TextView) view.findViewById(R.id.tv_time);
        tvbianhao = (TextView) view.findViewById(R.id.tv_bianhao);
        tvname = (TextView) view.findViewById(R.id.tv_name);
        tv_jilu = (TextView) view.findViewById(R.id.tv_jilu);
        tvleixing = (TextView) view.findViewById(R.id.tv_leixing);
        tvminaji = (TextView) view.findViewById(R.id.tv_mianji);
        tvyezhuname = (TextView) view.findViewById(R.id.tv_yezhuname);
        tvyezhuphone = (TextView) view.findViewById(R.id.tv_yezhuphone);
        tvtuijianren = (TextView) view.findViewById(R.id.tv_tuijianren);
        tvyixiangpinpai = (TextView) view.findViewById(R.id.tv_pinpai);
        tvyixiangkongtiao = (TextView) view.findViewById(R.id.tv_kongtiaoleixing);
        tvpayment = (TextView) view.findViewById(R.id.tv_payment);
        tvadd = (TextView) view.findViewById(R.id.tv_add);
        tvcontent = (TextView) view.findViewById(R.id.tv_content);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
            }
        });
        mAdapterex = SlimAdapter.create(SlimAdapterEx.class).addHeader(view)
                .register(R.layout.item_genjinjilu, new SlimInjector<RecordList.DataBean.DataBeans>() {
                    @Override
                    public void onInject(final RecordList.DataBean.DataBeans data, IViewInjector injector) {
                        injector.text(R.id.tv_title, data.getContent());
                        injector.text(R.id.tv_time, data.getCreate_time());
                        CustomGridView gridView = (CustomGridView) injector.findViewById(R.id.gv_pic);
                        if (data.getSmeta().size() == 0) {
                            gridView.setVisibility(View.GONE);
                        } else {
                            gridView.setVisibility(View.VISIBLE);
                            gridView.setAdapter(new com.zhy.adapter.abslistview.CommonAdapter<RecordList.DataBean.DataBeans.SmetaBean>
                                    (baseContext, R.layout.item_gvimg, data.getSmeta()) {
                                @Override
                                protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, RecordList.DataBean.DataBeans.SmetaBean item, int position) {
                                    ImageView imageView = viewHolder.getView(R.id.img);
                                    int wid = (App.wid - (CommonUtil.sp2px(mContext, 40))) / 3;
                                    CommonUtil.setwidhe(imageView, wid, wid);
                                    CommonUtil.setimg(mContext, item.getUrl(), R.drawable.moren, imageView);
                                }
                            });
                        }

                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                String[] aStrings = new String[data.getSmeta().size()];
                                for (int i = 0; i < data.getSmeta().size(); i++) {
                                    aStrings[i] = data.getSmeta().get(i).getUrl();
                                }
                                Intent intent = new Intent(baseContext,
                                        ImagePagerActivity.class);
                                // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                                        aStrings);
                                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,
                                        position);
                                startActivity(intent);
                            }
                        });
                    }
                }).attachTo(recycleLisst);
        huankuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baseContext, RepayPlan_A.class);
                Bundle bundleObject = new Bundle();
                bundleObject.putSerializable("list", (Serializable) xiangMuInfo.getData().getPayment_list());
                intent.putExtras(bundleObject);
                startActivity(intent);
            }
        });


    }

    XiangMuInfo xiangMuInfo;

    public void getdata(boolean b) {
        mRequest = NoHttp.createStringRequest(HttpIp.pdetail, Const.POST);
        mRequest.add("project_id", getIntent().getStringExtra("id"));
        getRequest(new CustomHttpListener<XiangMuInfo>(baseContext, true, XiangMuInfo.class) {
            @Override
            public void doWork(XiangMuInfo data, String code) {
                if ("1".equals(data.getCode())) {
                    xiangMuInfo = data;
                    String[] txt = new String[]{"已接洽", "已提交方案", "已签订合同", "已付款", "已验收", "已施工"};
//        状态（1已关闭；2已报备；3已接洽；4已提交方案；5已签合同；6已付款；7已施工；8已验收；）
                    timeLineView.builder().pointStrings(txt, 1).load();
//                    status/
                    switch (data.getData().getStatus()) {
                        case "1":
                            tv_status.setText("当前项目状态(" + "已关闭)");
                            break;
                        case "2":
                            tv_status.setText("当前项目状态(" + "已报备)");
                            break;
                        case "3":
                            tv_status.setText("当前项目状态(" + "已接洽)");
                            break;
                        case "4":
                            tv_status.setText("当前项目状态(" + "已提交方案)");
                            break;
                        case "5":
                            tv_status.setText("当前项目状态(" + "已签合同)");
                            break;
                        case "6":
                            tv_status.setText("当前项目状态(" + "已付款)");
                            break;
                        case "7":
                            tv_status.setText("当前项目状态(" + "已施工)");
                            break;
                        case "8":
                            tv_status.setText("当前项目状态(" + "已验收)");
                            break;
                    }
                    timeLineView.setStep(Integer.parseInt(data.getData().getStatus()) - 2);
                    tvtime.setText(data.getData().getCreate_time());
                    tvbianhao.setText(data.getData().getTransaction_number());
                    tvname.setText(data.getData().getTitle());
                    tvleixing.setText(data.getData().getProperty_name());
                    tvminaji.setText(data.getData().getArea() + "平方米");
                    tvyezhuname.setText(data.getData().getAddress_name());
                    tvyezhuphone.setText(data.getData().getAddress_tel());
                    tvtuijianren.setText(data.getData().getNickname());
                    tvyixiangpinpai.setText(data.getData().getBrand_name());
                    tvyixiangkongtiao.setText(data.getData().getProduct_type_name());
//                    支付方式（1分期。2全款）
                    if ("1".equals(data.getData().getPay_type())) {
                        tvpayment.setText("分期");
                    }
                    if ("2".equals(data.getData().getPay_type())) {
                        tvpayment.setText("全款");
                        huankuan.setVisibility(View.GONE);
                    }
                    if (null != data.getData().getPayment_list() && data.getData().getPayment_list().size() != 0) {
                        huankuan.setVisibility(View.VISIBLE);
                    }
                    tvadd.setText(data.getData().getArea_merger_name() + data.getData().getAddress());
                    tvcontent.setText(data.getData().getContent());
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                pager = 1;
                getlist();
            }
        }, b);
    }


    public void getlist() {
        mRequest = NoHttp.createStringRequest(HttpIp.record, Const.POST);
        mRequest.add("per_page", "15");
        mRequest.add("project_id", getIntent().getStringExtra("id"));
        mRequest.add("page", pager);
        getRequest(new CustomHttpListener<RecordList>(baseContext, true, RecordList.class) {
            @Override
            public void doWork(RecordList data, String code) {
                if ("1".equals(data.getCode())) {
                    if (pager == 1) {
                        datas.clear();
                    }
                    datas.addAll(data.getData().getData());
                    mAdapterex.updateData(datas).notifyDataSetChanged();
                    pager++;
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                swipeRefresh.setRefreshing(false);
                isLoadingMore = false;
                if (datas.size() == 0) {
                    tv_jilu.setText("暂无跟进记录");
                } else {
                    tv_jilu.setText("跟进记录");
                }
            }
        }, false);
    }

}
