package com.meida.liice;

import android.os.Bundle;
import android.os.SystemClock;

public class QiDongActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qi_dong);
        new Thread() {
            public void run() {
                SystemClock.sleep(1500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            StartActivity(MainActivity.class);
                        finish();
                    }
                });
            }
        }.start();
    }
}
