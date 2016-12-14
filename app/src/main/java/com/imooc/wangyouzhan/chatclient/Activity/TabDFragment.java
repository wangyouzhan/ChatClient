package com.imooc.wangyouzhan.chatclient.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangyouzhan on 2016/12/14.
 * Email 936804097@qq.com
 */

public class TabDFragment extends Fragment {

    private static final String TAG = "TabDFragment";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ----------");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ----------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView text = new TextView(getActivity());
        text.setText("tabD");
        text.setGravity(Gravity.CENTER);
        return text;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: -------------------");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: --------------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ------------------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ----------------");
    }
    

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: -----------------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: --------------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ---------------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ----------------");
    }
}
