package com.meida.ui.fg05;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.meida.MyView.CircleImageView;
import com.meida.liice.BaseActivity;
import com.meida.liice.HuanBangPhoneActivity;
import com.meida.liice.R;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 *
 * @author Administrator-LFC
 *         created at 2018/8/9 17:04
 */
public class PersonInfo_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.img_head_pinfo)
    CircleImageView imgHeadPinfo;
    @Bind(R.id.lay_head_pinfo)
    LinearLayout layHeadPinfo;
    @Bind(R.id.tv_name_pinfo)
    TextView tvNamePinfo;
    @Bind(R.id.lay_name_pinfo)
    LinearLayout layNamePinfo;
    @Bind(R.id.tv_tel_pinfo)
    TextView tvTelPinfo;
    @Bind(R.id.lay_tel_pinfo)
    LinearLayout layTelPinfo;
    @Bind(R.id.tv_sex_pinfo)
    TextView tvSexPinfo;
    @Bind(R.id.lay_sex_pinfo)
    LinearLayout laySexPinfo;
    @Bind(R.id.activity_ge_ren_zi_liao)
    LinearLayout activityGeRenZiLiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        changeTitle("个人资料");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(baseContext).load(PreferencesUtils.getString(baseContext, "logo")).error(R.mipmap.ico_lb084).into(imgHeadPinfo);
            tvNamePinfo.setText(PreferencesUtils.getString(baseContext, "nickname"));
        String mobile = PreferencesUtils.getString(baseContext, "user_tel");
        tvTelPinfo.setText(mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()));
//        tvTelPinfo.setText(PreferencesUtils.getString(baseContext, "user_tel"));
        switch (PreferencesUtils.getString(baseContext, "sex")) {//1男2女0保密
            case "1":
                tvSexPinfo.setText("男");
                break;
            case "2":
                tvSexPinfo.setText("女");
                break;
            case "0":
                tvSexPinfo.setText("保密");
                break;
        }
    }

    @OnClick({R.id.lay_head_pinfo, R.id.lay_name_pinfo, R.id.lay_tel_pinfo, R.id.lay_sex_pinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_head_pinfo:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        // TODO: 2017/1/5  相机权限
                        getPhoto();
                    }
                }, R.string.camera, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);


                break;
            case R.id.lay_name_pinfo:
                startActivity(new Intent(baseContext, SetNickName_A.class));
                break;
            case R.id.lay_tel_pinfo:
                startActivity(new Intent(baseContext, HuanBangPhoneActivity.class));
                break;
            case R.id.lay_sex_pinfo:
                sex();
                break;
        }
    }

    private void getPhoto() {

        final String[] stringItems = {"拍照", "从相册选择"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, layHeadPinfo);
        dialog.itemTextColor(getResources().getColor(R.color.black));
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                dialog.dismiss();
                if (position == 0) {
                    /**
                     * 调用快速拍照功能
                     */
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "/photo_loan.jpg")));
                    startActivityForResult(intent, 1);
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
                    startActivityForResult(intent, 2);
                }
            }
        });
    }

    File temp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case 1:
                    //                if (data == null) {
                    temp = new File(Environment.getExternalStorageDirectory() + "/photo_loan.jpg");
                    startPhotoZoom(Uri.fromFile(temp));
                    //                }
                    break;
                case 2:
                    if (data != null)
                        startPhotoZoom(data.getData());
                    break;
                case 3: // 取得裁剪后的图片
                    /**
                     * 非空判断大家一定要验证，如果不验证的话，
                     * 在剪裁之后如果发现不满意，要重新裁剪，丢弃
                     * 当前功能时，会报NullException，小马只
                     * 在这个地方加下，大家可以根据不同情况在合适的
                     * 地方做判断处理类似情况
                     */
                    if (data != null) {
                        setPicToView(data);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪图片方法实现
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        Uri cropUri = Uri.fromFile(new File(
                Environment.getExternalStorageDirectory().getPath() + "/crop.png"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪之后的图片数据并上传
     */

    private void setPicToView(Intent picdata) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/crop.png");
        file.exists();
        if (file.exists()) {
//            CommonUtil.showToask(baseContext, file.getName() + "");
            mRequest = NoHttp.createStringRequest(HttpIp.set_info, Const.POST);
            mRequest.add("user_logo", new FileBinary(new File(Environment.getExternalStorageDirectory().getPath() + "/crop.png")));//用户性别1男2女0保密
            getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                @Override
                public void doWork(Coommt data, String code) {
                    if ("1".equals(data.getCode())) {
                        PreferencesUtils.getString(baseContext, "logo", data.getData().getLogo());
                        CommonUtil.setcimg(baseContext, data.getData().getLogo(), R.mipmap.ico_lb084, imgHeadPinfo);
                    }
                }

                @Override
                public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                    if (obj != null) {
                        try {
                            showtoa(obj.getString("msg"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, true);
        }
    }

    private void sex() {
        final String[] stringItems = {"男", "女"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, laySexPinfo);
        dialog.itemTextColor(getResources().getColor(R.color.black));
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                if (position == 0) {
                    changesex(true, 1);
                } else {
                    changesex(true, 2);
                }
            }
        });
    }

    //    修改性别
    private void changesex(boolean b, final int index) {
        mRequest = NoHttp.createStringRequest(HttpIp.set_info, Const.POST);
        mRequest.add("user_sex", index);//用户性别1男2女0保密
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    tvSexPinfo.setText(index == 1 ? "男" : "女");
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {

                if (obj != null) {
                    try {
                        showtoa(obj.getString("msg"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

}
