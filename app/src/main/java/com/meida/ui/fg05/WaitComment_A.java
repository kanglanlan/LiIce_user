package com.meida.ui.fg05;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.MyView.CustomGridView;
import com.meida.adapter.PicturAdapter;
import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.MoRenAdd;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.willy.ratingbar.ScaleRatingBar;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.meida.share.Datas.ISRE;

/**
 * 待评价
 *
 * @author Administrator-LFC
 *         created at 2018/8/18 14:38
 */
public class WaitComment_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.img_head_wc)
    ImageView imgHeadWc;
    @Bind(R.id.tv_title_mc)
    TextView tvTitleMc;
    @Bind(R.id.tv_note_wc)
    TextView tvNoteWc;
    @Bind(R.id.ratbar01_mc)
    ScaleRatingBar ratbar01Mc;
    @Bind(R.id.ratbar02_mc)
    ScaleRatingBar ratbar02Mc;
    @Bind(R.id.ratbar03_mc)
    ScaleRatingBar ratbar03Mc;
    @Bind(R.id.edit_comment_mc)
    EditText editCommentMc;
    @Bind(R.id.gv_mc)
    CustomGridView gvMc;
    @Bind(R.id.btn_pay_mc)
    Button btnPayMc;
    @Bind(R.id.ll_top)
    LinearLayout ll_top;


    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private ArrayList<String> mSelectPathtwo = new ArrayList<String>();
    private PicturAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_comment);
        ButterKnife.bind(this);
        initView();

//        mRequest.add("type", getIntent().getStringExtra("type"));//	评价类型不能为空 1商品2兑换3报修
        if ("3".equals(getIntent().getStringExtra("type"))) {
            ll_top.setVisibility(View.GONE);
        }
    }

    private void initView() {
        changeTitle("待评价");
        mSelectPath.add("");
        adapter = new PicturAdapter(this, R.layout.item_img, mSelectPath);
        gvMc.setAdapter(adapter);
        gvMc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //创建PermissionUtil对象，参数为继承自V4包的 FragmentActivity
                if (mSelectPath.get(position).equals("")) {
                    int selectedMode = 1;
                    Intent intent = new Intent(baseContext,
                            MultiImageSelectorActivity.class);
                    // 是否显示拍摄图片
                    intent.putExtra("show_camera", true);
                    // 最大可选择图片数量
                    intent.putExtra("max_select_count", 9);
                    // 选择模式
                    intent.putExtra("select_count_mode", selectedMode);
                    // 默认选择
                    if (mSelectPath != null && mSelectPath.size() > 0) {
                        intent.putExtra("default_list", mSelectPath);
                    }
                    mSelectPath.remove(mSelectPath.size() - 1);
                    startActivityForResult(intent, 2);
                }
            }
        });
        initData();
    }

    private void initData() {
        CommonUtil.setimg(baseContext, getIntent().getStringExtra("img"), R.drawable.moren, imgHeadWc);
        tvTitleMc.setText(getIntent().getStringExtra("title"));
        tvNoteWc.setText(getIntent().getStringExtra("content"));
    }

    @OnClick(R.id.btn_pay_mc)
    public void onViewClicked() {
        int stars1 = (int) ratbar01Mc.getRating();
        int stars2 = (int) ratbar02Mc.getRating();
        int stars3 = (int) ratbar03Mc.getRating();
        Log.d("--lfc", "stars1:" + stars1 + " stars2:" + stars2 + " stars3:" + stars3);

        mRequest = NoHttp.createStringRequest(HttpIp.evaluate, Const.POST);
        mRequest.add("type", getIntent().getStringExtra("type"));//	评价类型不能为空 1商品2兑换3报修
        mRequest.add("order_id", getIntent().getStringExtra("id"));
        mRequest.add("product_id", getIntent().getStringExtra("pid"));
        mRequest.add("level_manner", stars1);
        mRequest.add("level_speed", stars2);
        mRequest.add("level_express", stars3);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("transaction_number"))) {
            mRequest.add("transaction_number", getIntent().getStringExtra("transaction_number"));
        }
        mRequest.add("content", editCommentMc.getText().toString());
        for (int i = 0; i < mSelectPathtwo.size(); i++) {
            mRequest.add("img_" + i, new FileBinary(new File(mSelectPathtwo.get(i))));
        }
        getRequest(new CustomHttpListener<MoRenAdd>(baseContext, true, MoRenAdd.class) {
            @Override
            public void doWork(MoRenAdd data, String code) {
                if ("1".equals(data.getCode())) {
                    ISRE = 1;
                    String type;
                    if ("3".equals(getIntent().getStringExtra("type"))) {
                        type = "2";
                    } else {
                        type = "1";
                    }
                    baseContext.startActivity(new Intent(baseContext, IntegralReward_A.class)
                            .putExtra("id", getIntent().getStringExtra("id"))
                            .putExtra("type", type)
                    );
                    finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (int i = 0; i < mSelectPath.size(); i++) {
            if (TextUtils.isEmpty(mSelectPath.get(i))) {
                mSelectPath.remove(i);
            }
        }
        if (resultCode == RESULT_OK) {
            mSelectPath = data
                    .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            StringBuilder sb = new StringBuilder();
            for (String p : mSelectPath) {
                sb.append(p);
                sb.append("\n");
            }
            mSelectPathtwo.clear();
            Luban.with(this)
                    .load(mSelectPath)                                   // 传人要压缩的图片列表
                    .ignoreBy(100)                                  // 忽略不压缩图片的大小
                    .setTargetDir(CommonUtil.getPath())                        // 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            mSelectPathtwo.add(file.getAbsolutePath());
                        }

                        @Override
                        public void onError(Throwable e) {
                            // TODO 当压缩过程出现问题时调用
                        }
                    }).launch();    //启动压缩
            if (mSelectPath.size() < 9) {
                mSelectPath.add("");
            }
            adapter = new PicturAdapter(this, R.layout.item_img, mSelectPath);
            gvMc.setAdapter(adapter);
        } else {
            if (mSelectPath.size() < 9) {
                mSelectPath.add("");
            }
            adapter = new PicturAdapter(this, R.layout.item_img, mSelectPath);
            gvMc.setAdapter(adapter);
        }


    }


}
