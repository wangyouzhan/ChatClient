package com.imooc.wangyouzhan.chatclient.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imooc.wangyouzhan.chatclient.IImoocAidl;
import com.imooc.wangyouzhan.chatclient.R;

public class AidlTestAcitivity extends AppCompatActivity implements View.OnClickListener {



    private Button btn_result;
    private EditText et_two;
    private EditText et_one;
    private EditText et_result;

    private IImoocAidl iImoocAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test_acitivity);

        btn_result = (Button) findViewById(R.id.btn_result);
        et_one = (EditText) findViewById(R.id.et_one);
        et_two = (EditText) findViewById(R.id.et_two);
        et_result = (EditText) findViewById(R.id.et_result);
        btn_result.setOnClickListener(this);
        bindService();
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {

        //绑定服务的时候
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iImoocAidl = IImoocAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iImoocAidl = null;

        }
    };


    @Override
    public void onClick(View v) {
        int one = Integer.parseInt(et_one.getText().toString());
        int two = Integer.parseInt(et_two.getText().toString());

        try {
            int result = iImoocAidl.add(one,two);

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    private void bindService() {


        Intent intent = new Intent(this, IRemoteService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
