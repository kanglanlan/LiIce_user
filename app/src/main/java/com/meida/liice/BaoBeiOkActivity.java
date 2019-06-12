package com.meida.liice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.meida.ui.fg05.MyReports_A;
import com.meida.ui.fg05.ServiceRecord_A;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoBeiOkActivity extends BaseActivity {


    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_look)
    Button btLook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_bei_ok);
        ButterKnife.bind(this);
        switch (getIntent().getStringExtra("tag")) {
            case "1":
                changeTitle("项目报备成功");
                tvContent.setText("您的资料我们已经收到，工作人员将在1-3个工作日内和客户取得联系，感谢你对立冰的支持！");
                btLook.setText("查看我的报备项目");
                break;
            case "2":
                changeTitle("一键报修");
                tvContent.setText("您的报修申请我们已经收到，工作人员将在1个工作日内和您取得联系，敬请耐心等待！");
                btLook.setText("查看我的报修记录");
                break;
        }
        switch (getIntent().getStringExtra("isshow")) {
            case "1":
                break;
            case "2":
                btLook.setVisibility(View.GONE);
                break;
        }
    }

    @OnClick(R.id.bt_look)
    public void onClick() {
        switch (getIntent().getStringExtra("tag")) {
            case "1":
                StartActivity(MyReports_A.class);
                finish();
                break;
            case "2":
                StartActivity(ServiceRecord_A.class);
                finish();
                break;
        }
    }
}
