package com.imooc.wangyouzhan.chatclient.scratch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.imooc.wangyouzhan.chatclient.R;

/**
 * Created by wangyouzhan on 2016/11/28.
 * Email 936804097@qq.com
 */

public class GuaGuaKa extends View {

    private Paint mOuterPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;


    private Bitmap mOutterBitmap;


//    private Bitmap bitmap;//最下层背景


    private String mText;
    private Paint mBackPaint;
    /**
     * 记录刮奖信息的文本宽和高
     */
    private Rect mTextBound;

    private int mTextSize;

    private int mTextColor = 0xff00f0;

    /**
     * 判断遮盖层是否超出阈值
     */
    private volatile boolean mComplete = false;


    public void setOnGuaGuaKaCompleteListener(OnGuaGuaKaCompleteListener mListener) {
        this.mListener = mListener;
    }

    private OnGuaGuaKaCompleteListener mListener;


    public interface OnGuaGuaKaCompleteListener {
        void complete();
    }


    public GuaGuaKa(Context context) {
        this(context,null);

    }

    public GuaGuaKa(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public GuaGuaKa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        initAttributeSet(context, attrs,defStyleAttr);

    }

    private void initAttributeSet(Context context, AttributeSet attrs,int defStyleAttr) {
        TypedArray a = null;
        try {

            a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GuaGuaKa,defStyleAttr,0);
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {

                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.GuaGuaKa_text:
                        mText = a.getString(attr);
                        break;
                    case R.styleable.GuaGuaKa_textSize:
                        mTextSize = (int) a.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics()));

                        break;
                    case R.styleable.GuaGuaKa_textColor:

                        mTextColor = a.getColor(attr, 0xff00f0);
                        break;
                    default:
                        break;
                }
            }


        } finally {
            if (a != null) {
                a.recycle();
            }
        }

    }


    public void setmText(String mText) {
        this.mText = mText;
        //设置文本的
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //初始化我们的bitmap
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        //设置回执path画笔的属性
        setupOutPaint();

        setUpBackPaint();

        //设置刮刮乐的从下到上的第二层的覆盖灰色图层
//        mCanvas.drawColor(Color.parseColor("#c0c0c0"));
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30, mOuterPaint);

        mCanvas.drawBitmap(mOutterBitmap, null, new Rect(0, 0, width, height), null);

    }

    /**
     * 设置获奖信息的
     */
    private void setUpBackPaint() {

        mBackPaint.setColor(mTextColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setTextSize(mTextSize);
        //获得当前画笔文本的宽和高
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                if (dx > 3 && dy > 3) {
                    mPath.lineTo(x, y);
                }

                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:

                new Thread(mRunnable).start();


                break;
            default:
                break;
        }

        invalidate();

        return true;

    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();

            float wipeArea = 0;
            float totalArea = w * h;

            Bitmap bitmap = mBitmap;
            int[] mPixels = new int[w * h];

            //获得Bitmap上的所有的像素信息
            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }

            if (wipeArea > 0 && totalArea > 0) {

                int percent = (int) (wipeArea * 100 / totalArea);
                Log.e("Tag", percent + "");


                if (percent > 60) {
                    //清除掉图层区域
                    mComplete = true;
                    postInvalidate();

                }
            }
        }
    };


    /**
     * 当手指移动的时候,我们不断的将path绘制在Bitmap上,最后将bitmap绘制到系统的canvas上,有些双缓冲的效果
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //设置刮刮乐最底层的图片
//        canvas.drawBitmap(bitmap, 0,0,null);
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, mBackPaint);


        if (mComplete) {
            if (mListener != null) {
                mListener.complete();//这里回调的中要弹出toast,需要在ui线程中
            }
        }

        if (!mComplete) {
            drawPath();

            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    private void drawPath() {

        mOuterPaint.setStyle(Paint.Style.STROKE);

        //设置绘图的mode    dest  / src 关系
        mOuterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mCanvas.drawPath(mPath, mOuterPaint);
    }

    private void setupOutPaint() {

        mOuterPaint.setColor(Color.parseColor("#c0c0c0"));
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setDither(true);
        mOuterPaint.setStrokeJoin(Paint.Join.ROUND);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStyle(Paint.Style.FILL);
        mOuterPaint.setStrokeWidth(30);
    }

    /**
     * 进行一些初始化操作
     */
    private void init() {

        mOuterPaint = new Paint();
        mPath = new Path();

//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.haizewang_150);
        mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.haizewang_150);

        mText = "谢谢惠顾";
        mTextBound = new Rect();
        mBackPaint = new Paint();
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics());

    }


}
