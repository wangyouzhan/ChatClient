package com.imooc.wangyouzhan.chatclient.Activity;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by wangyouzhan on 2016/12/8.
 * Email 936804097@qq.com
 */

public class ZoomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {


    private boolean mOnce = false;

    /**
     * 初始化缩放的值
     */
    private float mInitScale;

    private float mMidScale;

    /**
     * 最大初始化
     */
    private float mMaxScale;

    private Matrix mScaleMatrix;

    /**
     * 捕获用户多点触控
     */
    private ScaleGestureDetector mScaleGestureDetector;

    //--------自由移动
    /**
     * 记录上一次多点触控的数量
     */
    private int mLastPointerCount;

    private float mLastX;
    private float mLastY;

    private int mTouchSlop;
    private boolean isCanDrag;

    private  boolean isCheckLeftAndRight;
    private  boolean isCheckTopAndBottom;

    //---------------双击
    private GestureDetector mGestureDetector;
    private boolean isAutoScale;



    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScaleMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);

        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                if (isAutoScale)
                {
                    return true;
                }

                float x = e.getX();
                float y = e.getY();

                if (getScale() < mMidScale)
                {
//                    mScaleMatrix.postScale(mMidScale/ getScale(), mMidScale / getScale(), x, y);
//                    setImageMatrix(mScaleMatrix);
                    postDelayed(new AutoScaleRunnable(mMidScale, x, y), 16);
                    isAutoScale = true;

                }else{
//                    mScaleMatrix.postScale(mInitScale / getScale() , mInitScale/ getScale(), x, y);
//                    setImageMatrix(mScaleMatrix);
                    postDelayed(new AutoScaleRunnable(mInitScale, x, y), 16);
                    isAutoScale = true;
                }


                return true;
            }
        });
    }

    /**
     * 自动的放大与缩小
     */
    private class AutoScaleRunnable implements Runnable{

        private float mTargetScale;
        //缩放的中心点
        private float x;
        private float y;

        private final float BIGGER = 1.07F;
        private final float SMALL = 0.93F;

        private float tmpScale;

        public AutoScaleRunnable(float mTargetScale, float x, float y)
        {
            this.mTargetScale = mTargetScale;
            this.x = x;
            this.y = y;

            if (getScale() < mTargetScale)
            {
                tmpScale = BIGGER;
            }
            if (getScale() > mTargetScale)
            {
                tmpScale = SMALL;
            }
        }

        @Override
        public void run() {

            //进行缩放
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y);
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);

            float currentScale = getScale();

            if ((tmpScale > 1.0f && currentScale < mTargetScale)
                    || (tmpScale < 1.0f && currentScale > mTargetScale))
            {
                postDelayed(this, 16);
            }else{//设置为我们的目标值
                float scale = mTargetScale / currentScale;
                mScaleMatrix.postScale(scale, scale, x, y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);

                isAutoScale = false;

            }

        }
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

            /**
             * 如果图片宽度大于控件宽度,但是高度小于控件的高度,我们缩小
             */
            if (dw > width && dh < height){
                scale = width * 1.0f / dw;
            }

            /**
             * 如果图片的高度大于控件的高度,但是宽度小于控件的宽度,我们将其缩小。
             */
            if (dh > height && dw < width){
                scale = height * 10.f / dh;
            }

            if ((dw > width && dh > height) || (dw < width && dh < height)){
                scale = Math.min(width * 1.0f/ dw, height * 1.0f/ dh);
            }


            /**
             * 得到初始化时缩放的比例
             */
            mInitScale = scale;
            mMaxScale = mInitScale * 4;
            mMidScale = mInitScale * 2;

            //将图片移动到图片的中心
            int dx = getWidth() / 2 - dw /2;
            int dy = getHeight() / 2 - dh / 2;


            mScaleMatrix.postTranslate(dx,dy);
            mScaleMatrix.postScale(mInitScale,mInitScale, width / 2, height/ 2);


            setImageMatrix(mScaleMatrix);



            mOnce = true;
        }




    }

    /**
     * 获得图片放大缩小后的宽和高,以及l,r,t,b
     * @return
     */
    private RectF getMatrixRectF(){

        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();

        Drawable d = getDrawable();
        if (d != null){
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }

        return rectF;
    }


    /**
     * 在缩放的时候进行边界控制和位置控制
     */
    private void checkBorderAndCenterWhenScale() {

        RectF rect = getMatrixRectF();

        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        //缩放时进行边界检测,防止出现白边
        if (rect.width() >= width)
        {
            if (rect.left > 0)
            {
                deltaX  = -rect.left;
            }
            if (rect.right < width)
            {
                deltaX = width - rect.right;
            }
        }

        if (rect.height() >= height)
        {
            if (rect.top > 0){
                deltaY = -rect.top;
            }
            if (rect.bottom < height)
            {
                deltaY = height  - rect.bottom;
            }
        }

        //若果宽度或者高度小于控件的宽度或高,则居中
        if (rect.width() < width){
            deltaX = width / 2f - rect.right + rect.width() / 2f;
        }

        if (rect.height() < height)
        {
            deltaY = height / 2f - rect.bottom + rect.height() / 2f ;
        }

        mScaleMatrix.postTranslate(deltaX,deltaY);



    }


    /**
     * 获取当前图片的缩放的值
     * @return
     */
    public float getScale(){
        float[] values = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];

    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();

        if (getDrawable() == null){
            return true;
        }

        // 缩放的范围控制
        if ((scale < mMaxScale && scaleFactor > 1.0f) ||
                (scale > mInitScale && scaleFactor < 1.0f))
        {
            if(scale * scaleFactor < mInitScale){
                scaleFactor = mInitScale / scale;
            }
            if (scale * scaleFactor > mMaxScale){
                scale = mMaxScale / scale;
            }

            //缩放
            mScaleMatrix.postScale(scaleFactor, scaleFactor ,detector.getFocusX(), detector.getFocusY());

            checkBorderAndCenterWhenScale();

            setImageMatrix(mScaleMatrix);
        }

        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //双击
        if (mGestureDetector.onTouchEvent(event))
        {
            return true;
        }

        mScaleGestureDetector.onTouchEvent(event);


        float x = 0;
        float y = 0;
        //拿到多点触控的数量
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++)
        {
            x += event.getX(i);
            y += event.getY(i);
        }

        x /= pointerCount;
        y /= pointerCount;

        if (mLastPointerCount != pointerCount)
        {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }

        mLastPointerCount = pointerCount;
        RectF rectF = getMatrixRectF();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                if (rectF.width() > getWidth() + 0.01 || rectF.height() > getHeight() + 0.01)
                {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                if (rectF.width() > getWidth() + 0.01 || rectF.height() > getHeight() + 0.01)
                {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag){
                    isCanDrag = isMoveAction(dx, dy);
                }

                if (isCanDrag)
                {
                    if (getDrawable() != null)
                    {
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        //如果宽度小于控件宽度,不允许横向移动
                        if (rectF.width() < getWidth())
                        {
                            isCheckLeftAndRight = false;
                            dx = 0;
                        }
                        //如果高度小于控件高度,不允许纵向移动
                        if (rectF.height() < getHeight())
                        {
                            isCheckTopAndBottom = false;
                            dy = 0;
                        }

                        mScaleMatrix.postTranslate(dx, dy);

                        checkBorderWhenTranlate();
                        setImageMatrix(mScaleMatrix);
                    }
                }

                mLastX = x;
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                mLastPointerCount = 0;

                break;
        }




        return true;
    }

    /**
     * 当移动时进行边界检查
     */
    private void checkBorderWhenTranlate() {

        RectF rectF = getMatrixRectF();

        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();


        if (rectF.top > 0 && isCheckTopAndBottom)
        {
            deltaY = -rectF.top;
        }

        if (rectF.bottom < height && isCheckTopAndBottom)
        {
            deltaY = height - rectF.bottom;
        }

        if (rectF.left > 0 && isCheckLeftAndRight)
        {
            deltaX = -rectF.left;
        }

        if (rectF.right < width && isCheckLeftAndRight)
        {
            deltaX = width - rectF.right;
        }

        mScaleMatrix.postTranslate(deltaX, deltaY);




    }

    /**
     * 判断是否可以移动
     * @param dx
     * @param dy
     */
    private boolean isMoveAction(float dx, float dy) {

        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;

    }
}





























