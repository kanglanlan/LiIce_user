package com.meida.nohttp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meida.liice.LoginActivity;
import com.meida.share.Datas;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import static com.meida.liice.LoginActivity.loginactivity;


public abstract class CustomHttpListenermoney <T> implements HttpListener<String> {

    private JSONObject object;
    private Context context;
    private boolean isGson;
    private Class<T> dataM;

    public CustomHttpListenermoney(Context context, boolean isGson, Class<T> dataM) {
        this.context = context;
        this.isGson = isGson;
        this.dataM = dataM;
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.i("onSucceed", "请求成功：\n" + response.get());

        if (!response.get().matches("^\\{(.+:.+,*){1,}\\}$")) {
//            Toast.makeText(context, "网络数据格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            object = new JSONObject(response.get());
            try {
                if ("10001".equals(object.getString("code"))) {
                    if (Datas.LOGIN == 0) {
                        Datas.LOGIN = 1;
                        if(loginactivity!=null)
                        {
                            loginactivity.finish();
                        }
                        PreferencesUtils.putInt(context, "login", 0);
                        Toast.makeText(context, "请重新登录", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(dataM == null && "0".equals(object.getString("code"))) {
//                Toast.makeText(context, object.getString("msg"), Toast.LENGTH_SHORT).show();
                return;
            }
            if(!"0".equals(object.getString("code"))) {
                if(isGson && dataM != null) {
                    Gson gson = new Gson();
                    doWork(gson.fromJson(object.toString(), dataM), object.getString("code"));
                } else {
                    doWork((T) object, object.getString("code"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(!isGson && dataM == null && !"0".equals(object.getString("code"))) {
                    Toast.makeText(context, object.getString("msg"), Toast.LENGTH_SHORT).show();
                }
                onFinally(object, object.getString("code"), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void doWork(T data, String code);

    public void onFinally(JSONObject obj, String code, boolean isNetSucceed){ } // 解析完成，如要执行操作，可重写该方法。

    @Override
    public void onFailed(int what, Response<String> response) {
        Log.i("onFailed", "请求失败：\n" + response.get());

        // Toast.makeText(context, "网络请求数据失败", Toast.LENGTH_SHORT).show();

        onFinally(new JSONObject(), "-1", false); // JSON数据请求失败
    }


}
