package com.imooc.wangyouzhan.chatclient.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.imooc.wangyouzhan.chatclient.IImoocAidl;

/**
 * Created by wangyouzhan on 2016/11/15.
 * Email 936804097@qq.com
 */

public class IRemoteService extends Service {


    private static final String TAG = "IRemoteService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    private IBinder iBinder = new IImoocAidl.Stub(){

        @Override
        public int add(int num1, int num2) throws RemoteException {

            Log.d(TAG, "add: --------------------" + (num1 + num2));
            return num1 + num2;
        }
    };

}
