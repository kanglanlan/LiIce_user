package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZuLinActivity extends BaseActivity {
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_lin);
        ButterKnife.bind(this);
        /**
         * '102' => '空调租赁说明',
         '103' => '服务商加盟说明',
         '104' => '立冰介绍',
         '105' => '提现说明',
         '106' => '积分兑换规则',
         '107' => '补贴商品说明',
         '108' => '补贴积分说明',
         '109' => '核心服务商介绍',
         '110' => '服务商介绍',
         '111' => '积分解冻规则',
         '112' => '服务条款（商户端）',
         */

        switch (getIntent().getStringExtra("tag")) {
            case "1":
                changeTitle("详情");//自定义图文
                webview.loadDataWithBaseURL(null,getIntent().getStringExtra("info"), "text/html", "utf-8", null);

                break;
            case "2":
                changeTitle("详情");//自定义图文
                webview.loadUrl(getIntent().getStringExtra("info"));
                webview.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
                break;
            case "102":
                changeTitle("空调租赁");
                break;
            case "101":
                changeTitle("用户使用协议");
                break;
            case "104":
                changeTitle("立冰介绍");
                break;
            case "105":
                changeTitle("提现规则");
                break;
            case "112":
//                '112' => '服务条款（用户端）',
//                    '113' => '服务条款（商户端）',
                changeTitle("服务条款");
                break;
            case "108":
                changeTitle("补贴积分");
                break;
            case "109":
                changeTitle("核心服务商介绍");
                break;
            case "110":
                changeTitle("服务商介绍");
                break;
        }
        CommonUtil.websetting(baseContext, webview);
        if(!"1".equals(getIntent().getStringExtra("tag"))&&
                !"2".equals(getIntent().getStringExtra("tag")))
        {
            getdata();
        }
        webview.setWebChromeClient(new WebChromeClient() {
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
    }

    public void getdata() {
        mRequest = NoHttp.createStringRequest(HttpIp.document, Const.POST);
        mRequest.add("type", getIntent().getStringExtra("tag"));
        getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    webview.loadDataWithBaseURL(null, data.getData().getDetail(), "text/html", "utf-8", null);

                }
            }
        }, true);
    }
}
