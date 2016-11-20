package com.imooc.wangyouzhan.chatclient.viewpagerindicator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.List;

/**
 * Created by wangyouzhan on 2016/11/17.
 * Email 936804097@qq.com
 */

public class ViewPagerIndicator extends LinearLayout {

    private static final String TAG = "ViewPagerIndicator";


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取可见tab得数量

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);
        a.recycle();


        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));


    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private Paint mPaint;
    private Path mPath;

    private int mTriangleWidth;
    private int mTriangleHeight;

    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;//三角形的宽度
    private int mInitTranslationX; //初始化距离
    private int mTranslationX; //移动的宽度


    private static final int COUNT_DEFAULT_TAB = 4;
    private int mTabVisibleCount = 0;


    private List<String> mTitles;
    private static final int COLOR_TEXT_NORMAL = 0x77ffffff;
    private static final int COLOR_TEXT_HIGHLIGHT = 0x77ff33EE;


    private  final int DIMENSION_TRIANGLE_WIDTH = (int)(getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();

        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
        mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH);
        mInitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;//每个tab宽度的一半减去初始值的宽度

        initTriangle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if (cCount == 0) {
            return;
        }

        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }

        setItemClickEvent();

    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {

        mTriangleHeight = mTriangleWidth / 2;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();

    }

    /**
     * 指示器跟随手指进行滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {


        int tabWidth = getWidth() / mTabVisibleCount;

        mTranslationX = (int) (tabWidth * (offset + position));

        //容器移动,在tab处于移动至最后一个时候
        if (position >= (mTabVisibleCount - 2) && offset > 0 && getChildCount() > mTabVisibleCount) {

            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }

        //重绘制
        invalidate();

    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(genetateTextView(title));
            }
        }

        setItemClickEvent();
    }


    public void setVisibleTabCount(int cout) {
        mTabVisibleCount = cout;
    }


    public interface PageOnchangeListener{

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        public void onPageSelected(int position);
        public void onPageScrollStateChanged(int state);

    }

    public PageOnchangeListener pageOnchangeListener;

    public void setPageOnchangeListener(PageOnchangeListener pageOnchangeListener) {
        this.pageOnchangeListener = pageOnchangeListener;
    }

    /**
     * 根据title创建textView
     *
     * @param title
     * @return
     */
    private View genetateTextView(String title) {

        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);

        return tv;
    }

    private ViewPager mViewPager;

    public void setViewPager(ViewPager viewPager, final int pos) {
        mViewPager = viewPager;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: ---------" + positionOffset + "--" + positionOffsetPixels);

                //tabwith * positionOffset + postion * tabwidth
                scroll(position, positionOffset);
                if (pageOnchangeListener != null) {
                        pageOnchangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (pageOnchangeListener != null) {
                    pageOnchangeListener.onPageSelected(position);
                }
                higtLightText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (pageOnchangeListener != null) {
                    pageOnchangeListener.onPageScrollStateChanged(state);
                }
            }
        });


        mViewPager.setCurrentItem(pos);
        higtLightText(pos);
    }


    /**
     * 高亮
     * @param pos
     */
    private void higtLightText(int pos){
        for (int i = 0; i < getChildCount() ; i++){

            View view = getChildAt(i);
            if (view instanceof TextView){
                if (i == pos) {
                    ((TextView)view).setTextColor(COLOR_TEXT_HIGHLIGHT);
                }else{
                    ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
                }
            }
        }
    }


    /**
     * 设置点击
     */
    private void setItemClickEvent(){
        int cCount = getChildCount();

        for(int i = 0; i < cCount; i++){
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });

        }
    }

}
























