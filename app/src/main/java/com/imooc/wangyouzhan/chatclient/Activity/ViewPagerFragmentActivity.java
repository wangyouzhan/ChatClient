package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerFragmentActivi";


    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private String hello = "hello";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment);

        fragments.add(new TabAFragment());
        fragments.add(new TabBFragment());
        fragments.add(new TabCFragment());
        fragments.add(new TabDFragment());

        viewPager = (ViewPager) findViewById(R.id.id_viewPager);
        viewPager.setOffscreenPageLimit(4);

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(0, getSupportFragmentManager(), fragments, viewPager);
        adapter.setOnExtraPageChangeListener(new FragmentViewPagerAdapter.OnExtraPageChangeListener(){
            @Override
            public void onExtraPageSelected(int i) {
                super.onExtraPageSelected(i);
                Log.d(TAG, "onExtraPageSelected: -----------------" + i);
            }
        });


    }
}



















