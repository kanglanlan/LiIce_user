package com.meida.liice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.meida.MyView.CircleImageView;
import com.meida.model.Coommt;
import com.meida.utils.CommonUtil;
import com.meida.utils.DataCleanManager;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;


public class SettingActivity extends BaseActivity {

    @Bind(R.id.cb_xiaoxi)
    CheckBox cbXiaoxi;
    @Bind(R.id.tv_huancun)
    TextView tvHuancun;
    @Bind(R.id.img_hd)
    CircleImageView imgHd;
    @Bind(R.id.tv_banben)
    TextView tvBanben;
    private MaterialDialog materialDialog;
    public static Activity settingactivity;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        settingactivity = this;
        changeTitle("设置");
        try {
            tvHuancun.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
            tvBanben.setText("当前版本V" + CommonUtil.getVersion(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!PreferencesUtils.getBoolean(baseContext, "push")) {
            cbXiaoxi.setChecked(true);
            JPushInterface.resumePush(baseContext);
        } else {
            cbXiaoxi.setChecked(false);
            JPushInterface.stopPush(baseContext);
        }
        cbXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbXiaoxi.isChecked()) {
                    JPushInterface.resumePush(baseContext);
                    PreferencesUtils.putBoolean(baseContext, "push", false);
                } else {
                    JPushInterface.stopPush(baseContext);
                    PreferencesUtils.putBoolean(baseContext, "push", true);
                }
            }
        });
    }

    Coommt banBenM;

    @OnClick({R.id.ll_denglumima, R.id.ll_zhifumima, R.id.tv_about, R.id.tv_fakui, R.id.ll_huancun, R.id.ll_bb, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_denglumima:
                StartActivity(ChangemimaActivity.class);
                break;
            case R.id.ll_zhifumima:
                StartActivity(ChangePaypswActivity.class);
                break;
            case R.id.tv_about:
                StartActivity(AboutusActivity.class);
                break;
            case R.id.tv_fakui:
                startActivity(new Intent(baseContext, YiJianFanKuiActivity.class).putExtra("tag", "1"));
                break;
            case R.id.ll_huancun:
                materialDialog = new MaterialDialog(this);
                materialDialog.content("确定清除所有缓存")
                        .title("清除缓存")
                        .btnText("取消", "确定")
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideBottomExit())
                        .show();
                materialDialog.setOnBtnClickL(
                        new OnBtnClickL() { //left btn click listener
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                            }
                        },
                        new OnBtnClickL() { //right btn click listener
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                                DataCleanManager.clearAllCache(SettingActivity.this);
                                tvHuancun.setText("0k");
                            }
                        }
                );
                break;
            case R.id.ll_bb:
                Nonce.Updateversion(true, baseContext, new Nonce.CommCallback() {
                    @Override
                    public void doWork(Coommt string) {
//                        is_force  1强制更新2不强制
//                        type  1用户端2商户端
                        banBenM = string;
                        if (Integer.parseInt(CommonUtil.getVersionnum(baseContext))
                                < string.getData().getVersion_code()) {
                            show(string);
                        } else {
                            CommonUtil.showToask(SettingActivity.this, "暂无更新");
                        }

                    }
                });
                break;
            case R.id.tv_exit:
                //退出登录
                materialDialog = new MaterialDialog(this);
                materialDialog.content("确定要退出登录吗")
                        .title("退出登录")
                        .btnText("取消", "确定")
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideBottomExit())
                        .show();
                materialDialog.setOnBtnClickL(
                        new OnBtnClickL() { //left btn click listener
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                            }
                        },
                        new OnBtnClickL() { //right btn click listener
                            @Override
                            public void onBtnClick() {
//                                materialDialog.dismiss();
                                Nonce.dellogin(baseContext);
                                PreferencesUtils.putInt(baseContext, "login", 0);
                                StartActivity(ShouYeActivity.class);
                                finish();
                            }
                        }
                );
                break;
        }
    }

    /**
     * 显示版本更新弹窗
     */
    private void show(final Coommt banBenM) {

        LinearLayout tuichudia = (LinearLayout) getLayoutInflater().inflate(
                R.layout.popu_update, null);
        TextView queding = (TextView) tuichudia
                .findViewById(R.id.tv_gengxin);
        TextView tv_content = (TextView) tuichudia
                .findViewById(R.id.tv_content);
        TextView tv_title = (TextView) tuichudia
                .findViewById(R.id.tv_title);
        tv_title.setText("发现新版本：V " + banBenM.getData().getVersion_number());
        tv_content.setText(banBenM.getData().getContent());
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(tuichudia);
//                                is_force  1强制更新2不强制
        if ("1".equals(banBenM.getData().getIs_force())) {
            builder.setCancelable(false);
        }
        dialog = builder.show();
        queding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(banBenM.getData().getUrl())));

//                downLoadApk(banBenM.getData().getUrl());
            }

        });

    }

    /*
      * 从服务器中下载APK
      */
    protected void downLoadApk(final String path) {
        final ProgressDialog pd; // 进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在下载更新...");
        pd.show();
        //                                is_force  1强制更新2不强制
        if ("1".equals(banBenM.getData().getIs_force())) {
            pd.setCancelable(false);
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(path, pd);
                    installApk(file);
                    pd.dismiss(); // 结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /*
     * 从服务器下载apk
     */
    public File getFileFromServer(String path, ProgressDialog pd)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
//        if (hasSdcard()) {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        // 获取到文件的大小
        pd.setMax(conn.getContentLength() / 1024 / 1024);
        InputStream is = conn.getInputStream();
        File file = new File(getFilesDir(),
                "wdlt.apk");
//            File file = new File(Environment.getExternalStorageDirectory(),
//                    "wdlt.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len;
        int total = 0;
        while ((len = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
            total += len;
            // 获取当前下载量
            pd.setProgress(total / 1024 / 1024);
        }
        fos.close();
        bis.close();
        is.close();
        return file;
//        } else {
//            return null;
//        }
    }

    // 安装apk
    protected void installApk(File file) {
        String[] command = {"chmod", "777", getFilesDir() + "/wdlt.apk"};
        ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
            Intent intent = new Intent();
            // 执行动作
            intent.setAction(Intent.ACTION_VIEW);
            // 执行的数据类型
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
