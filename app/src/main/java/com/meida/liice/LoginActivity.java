package com.meida.liice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.meida.model.Login;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.Datas;
import com.meida.share.HttpIp;
import com.meida.utils.Nonce;
import com.meida.utils.PreferencesUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

import static com.meida.liice.ShouYeActivity.shouyeactivity;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.et_login_pass)
    EditText etLoginPass;
    private UMShareAPI mShareAPI;
    private UMAuthListener umAuthListener;
    int type = 1;//     type	string	是	登录类型:1手机号；2微信；3QQ；4微博
    private String open_key, profile_image_url, screen_name;//第三方登陆openid
    public static Activity loginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginactivity = this;
        mShareAPI = UMShareAPI.get(this);
        umAuthListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                mShareAPI.getPlatformInfo(baseContext, platform, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        if(share_media==SHARE_MEDIA.QQ){
                            open_key = map.get("uid");
                        }
                        if(share_media==SHARE_MEDIA.WEIXIN){
                            open_key = map.get("unionid");
                        }
                        profile_image_url = map.get("profile_image_url");
                        screen_name = map.get("screen_name");
//                        type	string	是	登录类型:1手机号；2微信；3QQ；4微博
                        login(type);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                    }
                });
            }

            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(baseContext, "失败了", Toast.LENGTH_LONG).show();
            }

            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(baseContext, "取消了", Toast.LENGTH_LONG).show();
            }
        };
    }

    @OnClick({R.id.img_login_back, R.id.bt_login, R.id.tv_login_sing, R.id.tv_login_rpass, R.id.tv_weixin, R.id.tv_ww})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_login_back:
                finish();
                break;
            case R.id.bt_login:
                if (etLoginPhone.getText().toString().length() != 11) {
                    showtoa("请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(etLoginPass.getText().toString())) {
                    showtoa("请输入密码");
                    return;
                }
                login(1);
                break;
            case R.id.tv_login_sing:
                StartActivity(ZhuCeActivity.class);
                break;
            case R.id.tv_login_rpass:
                StartActivity(WangJiMiMa1Activity.class);
                break;
            case R.id.tv_weixin:
                type = 2;//     type	string	是	登录类型:1手机号；2微信；3QQ；4微博
                mShareAPI.doOauthVerify(baseContext, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.tv_ww:
                type = 3;//     type	string	是	登录类型:1手机号；2微信；3QQ；4微博
                mShareAPI.doOauthVerify(baseContext, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }

    private void login(int types) {
        /**
         * type	string	是	登录类型:1手机号；2微信；3QQ；4微博		1
         user_tel	string	是	手机号		13783451041
         user_pass	string	是	密码		123456
         union_id	string	是	第三方开放平台union_id（同一个开放平台唯一的）
         */
        mRequest = NoHttp.createStringRequest(HttpIp.login, Const.POST);
        mRequest.add("type", types);//	1手机号；2微信；3QQ；4微博
        if (types == 1) {
            mRequest.add("user_tel", etLoginPhone.getText().toString());
            mRequest.add("user_pass", etLoginPass.getText().toString());
        } else {
            mRequest.add("union_id", open_key);
        }
        getRequest(new CustomHttpListener<Login>(baseContext, true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                Nonce.putinfo(baseContext, data);
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
//                        2 该手机号尚未注册 3密码不正确 4用户被禁用 5请注册绑定
                        showtoa(obj.getString("msg"));
                        if ("1".equals(obj.getString("code"))) {
                            JPushInterface.setAlias(baseContext, 1, "user_id_" + PreferencesUtils.getString(baseContext, "uid"));
                            PreferencesUtils.putInt(baseContext, "login", 1);
                            if (shouyeactivity != null) {
                                shouyeactivity.finish();
                            }
                            finish();
                        }
                        if ("5".equals(obj.getString("code"))) {
                            startActivity(new Intent(baseContext, BangDingPhoneActivity.class)
                                    .putExtra("open_key", open_key)
                                    .putExtra("user_logo", profile_image_url)
                                    .putExtra("user_nickname", screen_name)
                                    .putExtra("type", type + "")
                            );
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Datas.LOGIN=0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
