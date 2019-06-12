package com.meida.ui.fg05.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.fragment.BaseFragment;
import com.meida.liice.BuTieKongTiaoActivity;
import com.meida.liice.R;
import com.meida.liice.WoYaoTuiJianActivity;
import com.meida.model.ZhengJiFE;
import com.meida.ui.fg05.PersonInfo_A;
import com.meida.utils.Nonce;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.meida.share.Datas.SYTYPE;

/**
 * 赚取积分
 *
 * @author Administrator-LFC
 *         created at 2018/8/13 15:26
 */
@SuppressLint("ValidFragment")
public class FG_GetIntegral extends BaseFragment {

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


    private Adapter_GetIntegral adapter;
    private List<ZhengJiFE> list_data = new ArrayList<>();//数据源

    public FG_GetIntegral() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_get_integral, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        getData(true);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleLisst.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_GetIntegral(getActivity(), R.layout.item_getintegral, list_data);
        recycleLisst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 这个接口 可以的话  一页数据尽可能的多一点
     * 避免recyclerview 上拉加载失效
     * 原因： CoordinatoLayout 滑动内部处理了
     * 解决 ： 一页数据尽可能的排满整个屏幕 使recyclerview  可滑动
     *
     * @author Administrator-LFC
     * created at 2018/8/14 10:51
     */
    private void getData(boolean isload) {
        list_data.add(new ZhengJiFE("报备项目", R.mipmap.ico_lb085, 0, "报备项目奖励积分"));
        list_data.add(new ZhengJiFE("分享APP", R.mipmap.ico_lb086, 0, "分享APP奖励积分"));
        list_data.add(new ZhengJiFE("购买商品", R.mipmap.ico_lb087, 0, "购买商品奖励积分"));
        list_data.add(new ZhengJiFE("设置头像", R.mipmap.ico_lb088, 0, "设置头像奖励积分"));
        swipeRefresh.setRefreshing(false);
        adapter.notifyDataSetChanged();
        Nonce.task_status(getActivity(), new Nonce.StringCallback() {
            @Override
            public void doWork(String string) {
                if ("1".equals(string)) {
                    list_data.set(3, new ZhengJiFE("设置头像", R.mipmap.ico_lb088, 1, "设置头像奖励积分"));
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 赚积分
     */
    public class Adapter_GetIntegral extends CommonAdapter<ZhengJiFE> {

        public Adapter_GetIntegral(Context context, int layoutId, List<ZhengJiFE> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final ZhengJiFE s, final int position) {
//底线 显示
            LinearLayout line = holder.getView(R.id.line_itemgi);
            line.setVisibility((position == list_data.size() - 1) ? View.GONE : View.VISIBLE);
            //图片
            ImageView img_info = (ImageView) holder.getView(R.id.img_itemgi);
            img_info.setImageResource(s.getRe());
            holder.setText(R.id.tv_title_itemgi, s.getName());
            holder.setText(R.id.tv_note_itemgi, s.getJifen());
            TextView tv_play = holder.getView(R.id.tv_play_itemgi); //做任务
            TextView tv_over = holder.getView(R.id.tv_over_itemgi);//已完成
            if (s.getCheck() == 0) {
                tv_over.setVisibility(View.GONE);
                tv_play.setVisibility(View.VISIBLE);
            } else {
                tv_over.setVisibility(View.VISIBLE);
                tv_play.setVisibility(View.GONE);
            }
            tv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s.getCheck() == 0) {
                        switch (position) {
                            case 0:
                                SYTYPE = 1;
                                getActivity().finishAffinity();
                                break;
                            case 1:
                                StartActivity(WoYaoTuiJianActivity.class);
                                break;
                            case 2:
                                StartActivity(BuTieKongTiaoActivity.class);
                                break;
                            case 3:
                                StartActivity(PersonInfo_A.class);
                                break;
                        }
                    }

                }
            });
        }
    }


}
