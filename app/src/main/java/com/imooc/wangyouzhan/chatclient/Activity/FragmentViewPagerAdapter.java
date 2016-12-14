package com.imooc.wangyouzhan.chatclient.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangyouzhan on 2016/12/14.
 * Email 936804097@qq.com
 */

public class FragmentViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    private int currentPageIndex = 0;

    private OnExtraPageChangeListener onExtraPageChangeListener;

    private static final String TAG = "FragmentViewPagerAdapte";


    public FragmentViewPagerAdapter(int currentPageIndex, FragmentManager fragmentManager, List<Fragment> fragments, ViewPager viewPager) {
        this.currentPageIndex = currentPageIndex;
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);
        this.viewPager.addOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragments.get(position).getView());
        Log.d(TAG, "destroyItem: ---------------------" + position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.d(TAG, "instantiateItem: ------------" + position);

        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();

            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null)
        {
            container.addView(fragment.getView());
        }
        return fragment.getView();
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (null != onExtraPageChangeListener)
        {
            onExtraPageChangeListener.onExtraPageScrolled(position,positionOffset
            , positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        fragments.get(currentPageIndex).onPause();

        if (fragments.get(position).isAdded()){
            fragments.get(position).onResume();
        }
        currentPageIndex = position;

        if (null != onExtraPageChangeListener){
            onExtraPageChangeListener.onExtraPageSelected(position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (null != onExtraPageChangeListener){
            onExtraPageChangeListener.onExtraPageScrollStateChaged(state);
        }

    }

    public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }

    static class OnExtraPageChangeListener{
        public void onExtraPageScrolled(int i, float v, int i2){};
        public void onExtraPageSelected(int i){};
        public void onExtraPageScrollStateChaged(int i){};
    }
}
