package com.meida.liice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.meida.fragment.fragment1;
import com.meida.fragment.fragment2;
import com.meida.fragment.fragment3;
import com.meida.fragment.fragment4;
import com.meida.fragment.fragment5;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

import static com.meida.share.Datas.SYTYPE;
import static com.meida.share.Datas.setdatass;

public class MainActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener {
    fragment1 fr1;
    fragment2 fr2;
    fragment5 fr5;
    fragment3 fr3;
    fragment4 fr4;
    @Bind(R.id.rb_main_check_1)
    RadioButton rbMainCheck1;
    @Bind(R.id.rb_main_check_3)
    RadioButton rbMainCheck3;
    @Bind(R.id.rb_main_check_2)
    RadioButton rbMainCheck2;
    @Bind(R.id.rb_main_check_4)
    RadioButton rbMainCheck4;
    @Bind(R.id.rb_main_check_5)
    RadioButton rbMainCheck5;
    @Bind(R.id.rg_main_check)
    RadioGroup rgMainCheck;
    private FragmentTransaction transaction;
    private Fragment fragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setEnableGesture(false);
        setdatass();
        rgMainCheck.setOnCheckedChangeListener(this);
        rbMainCheck1.performClick();
        Nonce.getsys(baseContext);
        if (!PreferencesUtils.getBoolean(baseContext, "push")) {
            JPushInterface.resumePush(baseContext);
        } else {
            JPushInterface.stopPush(baseContext);
        }

        checkPermission(new BaseActivity.CheckPermListener() {
            @Override
            public void superPermission() {
                // TODO: 2017/1/5  相机权限
            }
        }, R.string.camera, android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE  );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (SYTYPE == 1) {
            SYTYPE = 0;
            rbMainCheck2.performClick();
        }
        if (PreferencesUtils.getInt(baseContext, "login") != 1) {
            if (rbMainCheck5.isChecked()) {
                rbMainCheck1.performClick();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = getSupportFragmentManager().beginTransaction();
        if (fragement != null) {
            transaction.hide(fragement);
        }
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_main_check_1:
                if (null == fr1) {
                    fr1 = fragment1.instantiation();
                    transaction.add(R.id.fl_main_fragment, fr1);
                }
                fragement = fr1;
                transaction.show(fragement);
                break;
            case R.id.rb_main_check_2:
                if (PreferencesUtils.getInt(baseContext, "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    rbMainCheck1.performClick();
                    return;
                }
                if (null == fr2) {
                    fr2 = fragment2.instantiation();
                    transaction.add(R.id.fl_main_fragment, fr2);
                }
                fragement = fr2;
                transaction.show(fragement);
                break;
            case R.id.rb_main_check_3:
                if (PreferencesUtils.getInt(baseContext, "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    rbMainCheck1.performClick();
                    return;
                }
                if (null == fr3) {
                    fr3 = fragment3.instantiation();
                    transaction.add(R.id.fl_main_fragment, fr3);
                }
                fragement = fr3;
                transaction.show(fragement);
                break;
            case R.id.rb_main_check_4:
                if (PreferencesUtils.getInt(baseContext, "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    rbMainCheck1.performClick();
                    return;
                }
                if (null == fr4) {
                    fr4 = fragment4.instantiation();
                    transaction.add(R.id.fl_main_fragment, fr4);
                }
                fragement = fr4;
                transaction.show(fragement);
                break;
            case R.id.rb_main_check_5:
                if (PreferencesUtils.getInt(baseContext, "login") != 1) {
                    StartActivity(ShouYeActivity.class);
                    rbMainCheck1.performClick();
                    return;
                }
                if (null == fr5) {
                    fr5 = fragment5.instantiation();
                    transaction.add(R.id.fl_main_fragment, fr5);
                }
                fragement = fr5;
                transaction.show(fragement);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    public void change(int numn) {
        switch (numn) {
            case 0:
                rbMainCheck2.performClick();
                break;
            case 1:
                rbMainCheck3.performClick();
                break;
            case 2:
                rbMainCheck4.performClick();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); // 调用双击退出函数
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            clearActivity();
            System.exit(0);
        }
    }

    public void doclick(View view) {
    }
}
