package com.imooc.wangyouzhan.chatclient.notification;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangyouzhan on 2016/11/14.
 * Email 936804097@qq.com
 */

public class DownloadService extends Service{


    private static final String TAG = "DownloadService";
    public static final int MSG_BIND = 0X2;
    public static final int MSG_UPDATE = 0X1;

    private Timer timer = new Timer();


    private Messenger mActivityMessenger = null;
    private int flag = 0;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_BIND)
            {
                mActivityMessenger = msg.replyTo;


                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = MSG_UPDATE;
                        message.arg1 = flag;
                        try {
                            mActivityMessenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        flag ++;
                    }
                },1000, 1000);
            }else if(msg.what == 3){//从activity发过来的信息

                Log.d(TAG, "handleMessage: -------------" + msg.obj);
            }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind: ------------" );

        //来自Activity的Messenger
        Messenger messenger = new Messenger(mHander);

        //返回messnger的binder
        return messenger.getBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        if (timer != null) {
            timer.cancel();
        }
        return super.onUnbind(intent);
    }

}
































