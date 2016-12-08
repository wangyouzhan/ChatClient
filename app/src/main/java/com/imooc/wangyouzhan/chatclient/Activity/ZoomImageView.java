package com.imooc.wangyouzhan.chatclient.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by wangyouzhan on 2016/12/8.
 * Email 936804097@qq.com
 */

public class ZoomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener{


    private boolean mOnce = false;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        getViewTreeObserver().addOnGlobalLayoutListener(this);


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }


    @Override
    public void onGlobalLayout() {

        if (!mOnce){
            //得到控件的宽和高
            int width = getWidth();
            int height = getHeight();

            //得到我们的图片,以及宽和高
            Drawable d = getDrawable();
            if (d == null)
                return;

            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();

            float scale = 1.0f;
            if (dw > width && dh < height){
                scale = width * 1.0f / dw;
            }

            if (dh > height && dw < width){
                scale = height * 10.f / dh;
            }

            if (dw > width && dh < height){
                scale = Math.min(width * 1.0f/ dw, height * 1.0f/ dh);
            }



            mOnce = true;
        }




    }
}





























