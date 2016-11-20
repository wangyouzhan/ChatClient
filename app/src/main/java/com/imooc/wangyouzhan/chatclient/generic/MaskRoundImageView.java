package com.imooc.wangyouzhan.chatclient.generic;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

/**
 * Created by wangyouzhan on 16/11/13.
 */

public class MaskRoundImageView extends ImageView {


    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;

    public MaskRoundImageView(Context context) {
        super(context);
        init();
    }

    public MaskRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_app);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //dst
        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 100, 100, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //src
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);

    }




    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(mOut, 0,0 , null);

    }
}
