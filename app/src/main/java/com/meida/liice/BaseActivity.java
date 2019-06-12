package com.meida.liice;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meida.nohttp.CallServer;
import com.meida.nohttp.CustomHttpListener;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.utils.CommonUtil;
import com.meida.utils.MD5Utils;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.meida.utils.SystemBarTintManager;
import com.yolanda.nohttp.rest.Request;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimAdapterEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hei.permission.EasyPermissions;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static com.meida.share.Params.app_token;


public class BaseActivity extends SwipeBackActivity implements
        TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener, EasyPermissions.PermissionCallbacks {
    public LinearLayoutManager linearLayoutManager;
    public LinearLayoutManager linearLayoutManager2;
    public GridLayoutManager gridLayoutManager;
    public StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SwipeBackLayout mSwipeBackLayout;

    public static final String IMG_TEST = "http://img15.3lian.com/2015/h1/34/d/24.jpg";

    public void ShowMethodManager(Activity activity, final EditText editText, final boolean show) {
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showInputMethodWindow() {

        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                },
                500);
    }

    /**
     * 网络请求对象.
     */
    public Request<String> mRequest;
    /**
     * 上下文context
     */
    public Activity baseContext;
    /**
     * SlimAdapter的adapter
     */
    public SlimAdapter mAdapter;
    public SlimAdapterEx mAdapterex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseContext = this;

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        AddActivity(baseContext);
        initSystemBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected void setEnableGesture(boolean cehua) {
        mSwipeBackLayout.setEnableGesture(cehua);
    }


    public void call(String phone) {
        if (TextUtils.isEmpty(phone)) {
            showtoa("无效电话号码");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
        startActivity(dialIntent);
    }

    //Nohttp请求
    public <T> void getRequest(CustomHttpListener<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce",Nonc );
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));
        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(baseContext));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(baseContext, "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(baseContext, "token"));
        }
        CallServer.getRequestInstance().add(baseContext, mRequest, customHttpListener, isloading);
    }

    //Nohttp请求
    public <T> void getRequest(CustomHttpListenermoney<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce",Nonc );
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));

        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(baseContext));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(baseContext, "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(baseContext, "token"));
        }
        CallServer.getRequestInstance().add(baseContext, mRequest, customHttpListener, isloading);
    }

    public void back(View view) {
        if (isExsitMianActivity(MainActivity.class)) {//存在这个类
            //进行操作
            finish();
        } else {//不存在这个类
            //进行操作
            StartActivity(MainActivity.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        } else {// 如果不是back键正常响应
            return super.onKeyDown(keyCode, event);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            if (isExsitMianActivity(MainActivity.class)) {//存在这个类
                //进行操作
                finish();
            } else {//不存在这个类
                //进行操作
                StartActivity(MainActivity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //改变标题
    public void changeTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }


    //显示吐丝
    public void showtoa(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    //页面跳转
    public void StartActivity(Class c) {
        Intent intent = new Intent(this, c);
        this.startActivity(intent);
    }

    //隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!"ShowInfoActivity".equals(getClass().getSimpleName())) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    hideSoftInput(v.getWindowToken());
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (!"ShowInfoActivity".equals(getClass().getSimpleName())) {
            if (v != null && (v instanceof EditText)) {
                int[] l = {0, 0};
                v.getLocationInWindow(l);
                int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                        + v.getWidth();
                return !(event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom);
            }
            // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        }
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     */
    private void hideSoftInput(IBinder token) {
        if (!"ShowInfoActivity".equals(getClass().getSimpleName())) {
            if (token != null) {
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(token,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时可以取消这个请求
        if (mRequest != null)
            mRequest.cancel();
    }

    private static List<Activity> listActivity = new ArrayList<Activity>();

    public static void AddActivity(Activity activity) {
        listActivity.add(activity);
    }


    public static void clearActivity() {
        for (int i = 0; i < listActivity.size(); i++) {
            if (listActivity.get(i) != null) {
                listActivity.get(i).finish();
            }
        }
    }

    public boolean hideInputWindow() {
        final View v = getWindow().peekDecorView();
        InputMethodManager imm = null;
        if (v != null && v.getWindowToken() != null) {
            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return imm.isActive();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }


    public void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(false);
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return
     */
    private boolean isExsitMianActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        ComponentName cmpName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    /**
     * 权限回调接口
     */
    protected static final int RC_PERM = 123;
    protected static int reSting = R.string.ask_again;//默认提示语句

    private CheckPermListener mListener;

    public interface CheckPermListener {
        //权限通过后的回调方法
        void superPermission();
    }

    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null)
                mListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString),
                    RC_PERM, mPerms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意了某些权限可能不是全部
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.setting, R.string.cancel, null, perms);
    }

    @Override
    public void onPermissionsAllGranted() {

        if (mListener != null)
            mListener.superPermission();//同意了全部权限的回调
    }
}
