package com.meida.liice;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.view.WindowManager;

import com.meida.utils.CrashHandler;
import com.meida.utils.Nonce;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import cn.jpush.android.api.JPushInterface;


/**
 * 作者 亢兰兰
 * 时间 2017/4/11 0011
 * 公司  郑州软盟
 */

public class App extends Application {

    private static App mInstance;
    public static int wid;
    public static int he;

    /**
     * 得到应用的上下文
     *
     * @return
     */
    public static App getApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initAndroidSDK();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        NoHttp.initialize(this);
        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒
                .setConnectTimeout(60 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒
                .setReadTimeout(60 * 1000));
        Nonce.getstime(mInstance);
        Logger.setTag("com.meida"); // 设置NoHttp打印Log的tag
        Logger.setDebug(true); // 开始NoHttp的调试模式, build.gradle中配置是否打印输出
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5ba0a169f1f55603bc000028");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wx517d42f7b5bf5d9a", "e3ebfd23f487558e2eefc0206dfb7a21");
        PlatformConfig.setQQZone("1107738885", "JCE6dY0PjCE0ZYmV");
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        wid = wm.getDefaultDisplay().getWidth();
        he = wm.getDefaultDisplay().getHeight();
        initImageLoader(this);
    }

    private void initAndroidSDK() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }


    public static void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024)) //建议内存设在5-10M,可以有比较好的表现
                .memoryCacheSize(5 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024) // 50 MB
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
