package com.imooc.wangyouzhan.chatclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Created by wangyouzhan on 2016/11/25.
 * Email 936804097@qq.com
 */

public class ViewButton extends Button {

    private Context mContext;

    public ViewButton(Context context) {
        super(context);
        this.mContext = context;
        scroller = new Scroller(mContext);
    }

    public ViewButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        scroller = new Scroller(mContext);
    }

    public ViewButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        scroller = new Scroller(mContext);
    }

    Scroller scroller = null;

    public void smoothScrollTo(int destX, int dextY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;

        scroller.startScroll(scrollX, 0 , deltaX, 0, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }
}
