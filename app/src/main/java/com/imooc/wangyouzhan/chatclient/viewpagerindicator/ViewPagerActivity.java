package com.imooc.wangyouzhan.chatclient.viewpagerindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {


    private static final String TAG = "ViewPagerActivity";
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;


    private List<String>  mTitles = Arrays.asList("短信1","收藏2","推荐3","短信4","收藏5","推荐6","短信7","收藏8","推荐9");
    private List<VpSimpleFragment> mContents = new ArrayList<VpSimpleFragment>();
    private FragmentPagerAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initViews();
        
        initDatas();

        mIndicator.setVisibleTabCount(3);
        mIndicator.setTabItemTitles(mTitles);

        mViewPager.setAdapter(mAdapter);

        mIndicator.setViewPager(mViewPager, 0);


    }

    private void initDatas() {

        for (String title: mTitles){
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };




    }

    private void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.id_viewPager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);


    }
}



































