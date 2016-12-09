package com.imooc.wangyouzhan.chatclient.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.imooc.wangyouzhan.chatclient.R;

/**
 * Created by wangyouzhan on 2016/12/9.
 * Email 936804097@qq.com
 */

public class LuckyPanSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private SurfaceHolder mHolder;
    private Canvas mCanvas;

    /**
     * 用于绘制的线程
     */
    private Thread t;

    /**
     * 线程控制的开关
     */
    private boolean isRunning = false;

    /**
     * 转盘的奖项
     */
    private String[] mStrs = new String[]{"单反相机", "IPAD", "恭喜发财", "IPHONE", "服装一套", "恭喜发财"};

    /**
     * 转盘的图片
     */
    private int[] mImgs = new int[]{R.mipmap.activity_live_active, R.mipmap.activity_star_active,
            R.mipmap.activity_star_nomal, R.mipmap.arrow_down, R.mipmap.arrow_left, R.mipmap.icon_app};

    /**
     * 转盘的颜色
     */
    private int[] mColor = new int[]{0xffffc300, 0xfff17e01, 0xfff17304, 0xff004455, 0xff335599, 0xff331199};


    /**
     * 与图片对应的bitmap数组
     */
    private Bitmap[] mImgsBitmap;


    private int mItemCount = 6;

    /**
     * 整个盘的范围
     */
    private RectF mRange = new RectF();

    /**
     * 整个盘的直径
     */
    private int mRadius;

    /**
     * 绘制文本的画笔
     */
    private Paint mTextPaint;

    /**
     * 滚动的速度
     */
    private double mSpeed = 10;

    private volatile float mStartAngle = 0;

    /**
     * 是否停止按钮
     */
    private boolean isShouldEnd;

    /**
     * 转盘的中心
     */
    private int mCenter;

    /**
     * 以paddingleft为准
     */
    private int mPadding;


    private Paint mArcPaint;

    private Bitmap mBgBitmpa = BitmapFactory.decodeResource(getResources(), R.mipmap.activity_star_active);

    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());

    public LuckyPanSurfaceView(Context context) {
        this(context, null);
    }

    public LuckyPanSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyPanSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHolder = getHolder();
        mHolder.addCallback(this);

        //可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常亮
        setKeepScreenOn(true);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());


        mPadding = getPaddingLeft();
        //半径
        mRadius = width - mPadding * 2;
        //中心点
        mCenter = width / 2;


        setMeasuredDimension(width, width);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //初始化绘制盘的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        //初始化绘制盘的画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(mTextSize);

        //初始化盘绘制的范围
        mRange = new RectF(mPadding, mPadding, mPadding + mRadius, mPadding + mRadius);

        //初始化图片
        mImgsBitmap = new Bitmap[mItemCount];

        for (int i = 0; i < mItemCount; i++) {
            mImgsBitmap[i] = BitmapFactory.decodeResource(getResources(), mImgs[i]);
        }

        isRunning = true;
        t = new Thread(this);

        t.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        isRunning = false;
    }


    @Override
    public void run() {

        //不断进行绘制
        while (isRunning) {
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();

            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {

                drawBg();

                //绘制盘块

                float tmpAngle = mStartAngle;
                float sweepAngle = 360 / mItemCount;

                for (int i = 0; i < mItemCount; i++) {
                    mArcPaint.setColor(mColor[i]);
                    //绘制盘块
                    mCanvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);

                    //绘制文本
                    drawText(tmpAngle, sweepAngle, mStrs[i]);

                    //绘制icon
                    drawIcon(tmpAngle, mImgsBitmap[i]);

                    tmpAngle += sweepAngle;
                }

                mStartAngle += mSpeed;

                //如果点击了停止按钮
                if (isShouldEnd)
                {
                    mSpeed = -1;
                }
                if (mSpeed <= 0)
                {
                    mSpeed = 0;
                    isShouldEnd = false;
                }

            }
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }

        }


    }

    /**
     * 绘制icon
     *
     * @param tmpAngle
     * @param bitmap
     */
    private void drawIcon(float tmpAngle, Bitmap bitmap) {

        //设置图片的宽度为直径的1/8
        int imgWidth = mRadius / 8;

        float angle = (float) ((tmpAngle + 360 / mItemCount / 2f) * Math.PI / 180f);

        int x = (int) (mCenter + mRadius / 2 / 2 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 2 / 2 * Math.sin(angle));

        //确定那个图片位置
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2);
        mCanvas.drawBitmap(bitmap, null, rect, null);

    }

    /**
     * 绘制文本
     *
     * @param tmpAngle
     * @param sweepAngle
     * @param mStr
     */
    private void drawText(float tmpAngle, float sweepAngle, String mStr) {

        Path path = new Path();
        path.addArc(mRange, tmpAngle, sweepAngle);

        //利用水平偏移量让文字居中

        float textWidth = mTextPaint.measureText(mStr);
        int hOffset = (int) (mRadius * Math.PI / mItemCount / 2 - textWidth / 2);
        int vOffset = mRadius / 2 / 6;

        mCanvas.drawTextOnPath(mStr, path, hOffset, vOffset, mTextPaint);

    }

    /**
     * 绘制背景
     */
    private void drawBg() {
        mCanvas.drawColor(0xFFFFFFFF);
        mCanvas.drawBitmap(mBgBitmpa, null, new Rect(mPadding / 2, mPadding / 2, getMeasuredWidth() - mPadding / 2, getMeasuredHeight() - mPadding / 2), null);

    }

    public void luckyStart(int index)
    {
        //计算每一项的角度
        int angle = 360 / mItemCount;

        //计算每一项的中奖范围
        float from = 270 - (index + 1) * angle;
        float end = from + angle;

        //设置停下来需要旋转的距离
        float targetFrom = 4 * 360 + from;
        float targetEnd = 4 * 360 + end;

        //等差数列求值
        float v1 = (float) ((-1 + Math.sqrt(1 + 8 * targetFrom)) / 2);
        float v2 = (float) ((-1 + Math.sqrt(1 + 8 * targetEnd)) / 2);


        mSpeed = v1 + Math.random()*(v2 -v1);

        isShouldEnd = false;
    }

    public void luckyEnd()
    {
        mStartAngle = 0;
        isShouldEnd = true;
    }

    /**
     * 判断是否在旋转
     * @return
     */
    public boolean isStart()
    {
        return mSpeed != 0;
    }

    public boolean isShouldEnd(){
        return isShouldEnd;
    }




}
















