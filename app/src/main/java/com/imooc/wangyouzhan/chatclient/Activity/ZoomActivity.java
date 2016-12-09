package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

public class ZoomActivity extends AppCompatActivity {

    private ViewPager id_viewPager;

    private int[] mImgs = new int[]{R.mipmap.haizewang_23, R.mipmap.haizewang_150,R.mipmap.haizewang_2};

    private ImageView[] mImageViews = new ImageView[mImgs.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        id_viewPager = (ViewPager) findViewById(R.id.id_viewPager);
        id_viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ZoomImageView imageView = new ZoomImageView(getApplicationContext());
                imageView.setImageResource(mImgs[position]);
                container.addView(imageView);
                mImageViews[position] = imageView;
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);

            }

            @Override
            public int getCount() {
                return mImgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view == object;
            }
        });

    }
}






































