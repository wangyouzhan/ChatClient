package com.imooc.wangyouzhan.chatclient.notification;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.imooc.wangyouzhan.chatclient.R;


/**
 * service和activity之间使用messenger通信
 */
public class MessengerTestActivity extends AppCompatActivity {

    private static final String TAG = "MessengerTestActivity";

    private EditText editText;
    private ServiceConnection serviceConnection;

    private Messenger mServiceMessenger;
    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  DownloadService.MSG_UPDATE:
                    Log.d(TAG, "handleMessage: ------------:" + msg.arg1 );
                break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        editText = (EditText) findViewById(R.id.et_content);


        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                //获取Service中的Messenger对象
                mServiceMessenger = new Messenger(binder);

                Messenger messenger = new Messenger(mHander);

                //创建消息
                Message msg = new Message();
                msg.what = DownloadService.MSG_BIND;
                msg.replyTo = messenger;
                //使用Service的Messenger发送Activity中的Messenger

                try {
                    mServiceMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        findViewById(R.id.btn_binder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MessengerTestActivity.this, DownloadService.class);
                bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);

            }
        });

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                Message message = new Message();
                message.what = 3;
                message.obj = string;
                try {
                    mServiceMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }


    }
}
