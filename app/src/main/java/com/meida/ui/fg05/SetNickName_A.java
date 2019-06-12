package com.meida.ui.fg05;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meida.liice.BaseActivity;
import com.meida.liice.R;
import com.meida.model.Coommt;
import com.meida.nohttp.CustomHttpListener;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.meida.utils.PreferencesUtils;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetNickName_A extends BaseActivity {

    @Bind(R.id.img_title_back)
    ImageView imgTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title_rigth)
    ImageView imgTitleRigth;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name_snn)
    EditText etNameSnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_nick_name);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        changeTitle("设置真实姓名");
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText("保存");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNameSnn.getText().toString())) {
                    showtoa("请输入真实姓名");
                    return;
                }
                mRequest = NoHttp.createStringRequest(HttpIp.set_info, Const.POST);
                mRequest.add("user_nickname", etNameSnn.getText().toString());
                getRequest(new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Coommt data, String code) {
                        if ("1".equals(data.getCode())) {
                            PreferencesUtils.putString(baseContext, "nickname", etNameSnn.getText().toString());
                            finish();
                        }
                    }
                    @Override
                    public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {

                        if (obj != null) {
                            try {
                                showtoa(obj.getString("msg"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);

            }
        });
        etNameSnn.setText(PreferencesUtils.getString(baseContext, "nickname"));
    }

    @OnClick(R.id.img_del)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(etNameSnn.getText().toString())) {
            etNameSnn.setText("");
        }
    }
}
