package com.meida.liice;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.meida.fragment.fragment4;

import butterknife.ButterKnife;

import static com.meida.share.Datas.ISBACK;

public class WoYaoTuiJianActivity extends BaseActivity {
    private FragmentTransaction transaction;
    private fragment4 fr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_yao_tui_jian);
        ButterKnife.bind(this);
        ISBACK=1;
        transaction = getSupportFragmentManager().beginTransaction();
        fr4 = fragment4.instantiation();
        transaction.add(R.id.fl_main_fragment, fr4);
        transaction.show(fr4);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ISBACK=0;
    }
}
