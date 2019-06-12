package com.meida.utils;

import android.content.Context;

import java.io.InputStream;

/**
 * 读取Assets目录下面的json文件
 * Created by liji on 2016/5/6.
 */
public class JAssetsUtils {
    public static String getJson(Context context, String fileName) {
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultString;
    }
}
