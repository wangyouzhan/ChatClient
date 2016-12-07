package com.imooc.wangyouzhan.chatclient.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import com.imooc.wangyouzhan.chatclient.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BottomTabsActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();
    private String[] mTitles = new String[]{"First Fragment1",
            "First Fragment2", "First Fragment3", "First Fragment4"};

    private ChangeColorIconWithText one;
    private ChangeColorIconWithText two;
    private ChangeColorIconWithText three;
    private ChangeColorIconWithText fourth;


    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        showOverflowButton();
        if (getActionBar() != null) {
            getActionBar().setDisplayShowHomeEnabled(false);

        }


        initView();

        initData();

        initEvent();

        mViewPager.setAdapter(mAdapter);


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initEvent() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        fourth.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.d("dd", "postion=" + positionOffset + "--pos:" + positionOffsetPixels);

                if (positionOffset > 0){
                    ChangeColorIconWithText left = mTabIndicators.get(position);
                    ChangeColorIconWithText right = mTabIndicators.get(position + 1);

                    left.setIconAlpha(1- positionOffset);
                    right.setIconAlpha(positionOffset);
                }


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initData() {

        for (String title : mTitles) {

            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, title);
            tabFragment.setArguments(bundle);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };

    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.view_pager_one);

        one = (ChangeColorIconWithText) findViewById(R.id.icon_one);
        mTabIndicators.add(one);
        two = (ChangeColorIconWithText) findViewById(R.id.icon_two);
        mTabIndicators.add(two);
        three = (ChangeColorIconWithText) findViewById(R.id.icon_three);
        mTabIndicators.add(three);
        fourth = (ChangeColorIconWithText) findViewById(R.id.icon_fourth);
        mTabIndicators.add(fourth);


        one.setIconAlpha(1.0f);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_select, menu);

        return true;
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {

                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }


        return true;
    }

    private void showOverflowButton() {


        try {

            ViewConfiguration conf = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(conf, false);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        resetOtherTabs();

        switch (v.getId()) {

            case R.id.icon_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);

                break;
            case R.id.icon_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);

                break;
            case R.id.icon_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);

                break;
            case R.id.icon_fourth:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);


                break;


        }


    }

    /**
     * 重置其他的tabindicator的颜色
     */
    private void resetOtherTabs() {

        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }

    }
}















