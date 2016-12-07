package com.imooc.wangyouzhan.chatclient.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangyouzhan on 2016/12/6.
 * Email 936804097@qq.com
 */

public class TabFragment extends Fragment {


    private String mTitle = "Default";
    public static final String TITLE = "title";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null){
            getArguments().getString(TITLE);
            mTitle = getArguments().getString(TITLE);
        }


        TextView tv = new TextView(getActivity());
        tv.setTextSize(30);
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}






















