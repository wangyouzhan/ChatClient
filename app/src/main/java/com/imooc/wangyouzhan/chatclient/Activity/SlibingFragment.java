package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangyouzhan on 2016/12/7.
 * Email 936804097@qq.com
 */

public class SlibingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        TextView tv = new TextView(getActivity());

        Bundle bundle =  getArguments();
        String text = bundle.getString("text");
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}

















