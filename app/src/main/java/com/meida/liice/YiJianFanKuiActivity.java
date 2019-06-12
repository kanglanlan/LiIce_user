package com.meida.liice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.meida.MyView.CustomGridView;
import com.meida.adapter.PicturAdapter;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.meida.utils.CommonUtil.getPath;

public class YiJianFanKuiActivity extends BaseActivity {

    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.gv_pic)
    CustomGridView gvYj;
    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private PicturAdapter adapter;
    public static ArrayList<String> mSelectPathtwo=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian_fan_kui);
        ButterKnife.bind(this);
        switch (getIntent().getStringExtra("tag")) {
            case "1":
                changeTitle("意见反馈");
                break;
        }
        mSelectPathtwo = new ArrayList<String>();
        init();
    }
    private void init() {
        mSelectPath.add("");
        adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
        gvYj.setAdapter(adapter);
        gvYj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            mSelectPathtwo.clear();
            Luban.with(this)
                    .load(mSelectPath)                                   // 传人要压缩的图片列表
                    .ignoreBy(100)                                  // 忽略不压缩图片的大小
                    .setTargetDir(getPath())                        // 设置压缩后文件存储位置
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
            adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
            gvYj.setAdapter(adapter);
        } else {
            if (mSelectPath.size() < 9) {
                mSelectPath.add("");
            }
            adapter = new PicturAdapter(this, R.layout.item_imgs, mSelectPath);
            gvYj.setAdapter(adapter);
        }


    }
    @OnClick(R.id.bt_tijiao)
    public void onClick() {
        if (TextUtils.isEmpty(etContent.getText().toString())) {
            showtoa("请填写问题和意见");
            return;
        }
        mRequest = NoHttp.createStringRequest(HttpIp.feedback, Const.POST);
        mRequest.add("content", etContent.getText().toString());
        for (int i = 0; i < mSelectPathtwo.size(); i++) {
            mRequest.add("img_" + i, new FileBinary(new File(mSelectPathtwo.get(i))));
        }
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
                        showtoa(obj.getString("msg"));
                        if ("1".equals(obj.getString("code"))) {
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);


    }
}
