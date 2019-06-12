package com.meida.liice;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.PreferencesUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.meida.utils.CommonUtil.getPath;

public class ShiMingRenZhengActivity extends BaseActivity {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.img_zheng)
    ImageView imgZheng;
    @Bind(R.id.img_fan)
    ImageView imgFan;
    private File file1, file2;
    int names, names2;
    private int tag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_ming_ren_zheng);
        ButterKnife.bind(this);
        changeTitle("实名认证");
        tvTitleRight.setText("保存");
        names = PreferencesUtils.getInt(baseContext, "num");
        names2 = names + 1;
        PreferencesUtils.putInt(baseContext, "num", names2 + 1);
    }
    @OnClick({R.id.tv_title_right, R.id.img_zheng, R.id.img_fan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    showtoa("请输入身份证姓名");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    showtoa("请输入身份证号");
                    return;
                }
                if (null == file1) {
                    showtoa("请选择身份证正面照片");
                    return;
                }
                if (null == file2) {
                    showtoa("请选择身份证反面照片");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.certification, Const.POST);
                mRequest.add("card_name", etName.getText().toString());
                mRequest.add("card_number", etCode.getText().toString());
                mRequest.add("card_front", new FileBinary(file1));
                mRequest.add("card_back", new FileBinary(file2));
                getRequest(new CustomHttpListener<Comment>(this, true, Comment.class) {
                    @Override
                    public void doWork(Comment data, String code) {
                    }
                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                        super.onFinally(obj, code, isNetSucceed);
                        if (obj != null) {
                            try {
                                showtoa(obj.getString("msg"));
                                if ("1".equals(obj.getString("code"))) {
                                    PreferencesUtils.putString(baseContext,"card_status","1");
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
                break;
            case R.id.img_zheng:
                tag = 1;//zheng   2zheng
                showtouxiang();
                break;
            case R.id.img_fan:
                tag = 2;//zheng   2反
                showtouxiang();
                break;
        }
    }

    public void showtouxiang() {
        final String[] stringItems = {"拍照", "从相册选择"};
        ActionSheetDialog dialog = null;
        switch (tag) {
            case 1:
                dialog = new ActionSheetDialog(this, stringItems, imgZheng);
                break;
            case 2:
                dialog = new ActionSheetDialog(this, stringItems, imgFan);
                break;
        }
        dialog.itemTextColor(getResources().getColor(R.color.black));
        dialog.isTitleShow(false).show();
        final ActionSheetDialog finalDialog = dialog;
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                finalDialog.dismiss();
                if (position == 0) {
                    /**
                     * 调用快速拍照功能
                     */
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    switch (tag) {
                        case 1:
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "/photo" + names + ".jpg")));
                            break;
                        case 2:
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "/photo" + names2 + ".jpg")));
                            break;
                    }

                    startActivityForResult(intent, 4);
                } else {
                    intent = new Intent(Intent.ACTION_PICK, null);
                    /**
                     * 下面这句话，与其它方式写是一样的效果，如果：
                     * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                     * intent.setType(""image/*");设置数据类型
                     * 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                     */
                    intent.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            "image/*");
                    startActivityForResult(intent, 5);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 4:
                switch (tag) {
                    case 1:
                        ImageLoader.getInstance().displayImage("file://" + Environment.getExternalStorageDirectory() + "/photo" + names + ".jpg", imgZheng);
                        Luban.with(this)
                                .load(Environment.getExternalStorageDirectory() + "/photo" + names + ".jpg")                                   // 传人要压缩的图片列表
                                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                                .setTargetDir(getPath())                        // 设置压缩后文件存储位置
                                .setCompressListener(new OnCompressListener() { //设置回调
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {

                                        file1 = file;
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();    //启动压缩
                        break;
                    case 2:
                        ImageLoader.getInstance().displayImage("file://" + Environment.getExternalStorageDirectory() + "/photo" + names2 + ".jpg", imgFan);
                        Luban.with(this)
                                .load(Environment.getExternalStorageDirectory() + "/photo" + names2 + ".jpg")                                   // 传人要压缩的图片列表
                                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                                .setTargetDir(getPath())                        // 设置压缩后文件存储位置
                                .setCompressListener(new OnCompressListener() { //设置回调
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        file2 = file;
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();    //启动压缩
                        break;
                }
                break;
            case 5:
                if (data != null) {
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    final String path = cursor.getString(columnIndex);  //获取照片路径
                    cursor.close();
                    Luban.with(this)
                            .load(path)                                   // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(getPath())                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    Log.i("luban","开始");
                                }

                                @Override
                                public void onSuccess(File file) {
                                    Log.i("luban","成功");
                                    switch (tag) {
                                        case 1:
                                            file1 = file;
                                            ImageLoader.getInstance().displayImage("file://" + path, imgZheng);
                                            break;
                                        case 2:
                                            file2 = file;
                                            ImageLoader.getInstance().displayImage("file://" + path, imgFan);
                                            break;
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("luban","错误");
                                }
                            }).launch();    //启动压缩
                }
                break;
        }
    }
}
