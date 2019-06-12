package com.meida.utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class RMService extends Service {
    private MyReceiver receiver;
    public static final String action = "jason.broadcast";
    String referch;
    int student_id;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * ���ô������ʽ����ע��㲥��������
         */
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.servicedemo4");
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        receiver = null;
    }

    public void callInService() {
        Intent intent = new Intent(action);
        intent.putExtra("attid", student_id);
        sendBroadcast(intent);
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            referch = intent.getExtras().getString("refrech");
            System.out.println("eeeeee" + referch);
            if (referch.equals("1")) {
                student_id = 1;
            }
            if (referch.equals("2")) {
                student_id = 2;
            }
            if (referch.equals("3")) {
                student_id = 3;
            }
            if (referch.equals("4")) {
                student_id = 4;
            }

            callInService();
        }

    }
}
