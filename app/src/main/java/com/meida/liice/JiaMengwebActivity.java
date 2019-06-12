package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiaMengwebActivity extends BaseActivity {

    @Bind(R.id.webv)
    WebView webv;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_mengweb);
        ButterKnife.bind(this);
        changeTitle("服务商加盟");
        CommonUtil.websetting(baseContext, webv);
        webv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        mRequest = NoHttp.createStringRequest(HttpIp.document, Const.POST);
        mRequest.add("type","103");
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    webv.loadDataWithBaseURL(null, data.getData().getDetail(), "text/html", "utf-8", null);
                }
            }
        }, true);
    }

    @OnClick(R.id.bt_tianxie)
    public void onClick() {

        if(PreferencesUtils.getInt(baseContext,"login")!=1)
        {
            StartActivity(ShouYeActivity.class);
            return;
        }
        StartActivity(JiaMengActivity.class);
        finish();
    }
}
