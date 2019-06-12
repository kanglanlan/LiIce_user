package com.meida.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者 亢兰兰
 * 时间 2018/6/13 0013
 * 公司  郑州软盟
 */

public class SaveImageUtils {



    public static void saveImageToGallerys(Context context, Bitmap bmp) {
        if (bmp == null){
            CommonUtil.showToast(context, "保存出错");
            return;
        }
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            CommonUtil.showToast(context, "文件未发现");
            e.printStackTrace();
        } catch (IOException e) {
            CommonUtil.showToast(context, "保存出错");
            e.printStackTrace();
        }catch (Exception e){
            CommonUtil.showToast(context, "保存出错");
            e.printStackTrace();
        }

        // 最后通知图库更新
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        CommonUtil.showToast(context, "保存成功");
    }

}
