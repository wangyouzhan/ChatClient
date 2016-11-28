package com.imooc.wangyouzhan.chatclient.recycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

public class RefreshRecyclerViewActivity extends AppCompatActivity {

    private SwipeRefreshLayout activity_refresh_recycler_view;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view);

        tv_show = (TextView) findViewById(R.id.tv_show);

        activity_refresh_recycler_view = (SwipeRefreshLayout) findViewById(R.id.activity_refresh_recycler_view);
        activity_refresh_recycler_view.setColorSchemeResources(android.R.color.holo_blue_dark,android.R.color.holo_green_dark, android.R.color.holo_green_light);;

        activity_refresh_recycler_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tv_show.setText("正在刷新");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_show.setText("刷新完成");
                        activity_refresh_recycler_view.setRefreshing(false);
                    }
                }, 6000);

            }
        });

    }
}































