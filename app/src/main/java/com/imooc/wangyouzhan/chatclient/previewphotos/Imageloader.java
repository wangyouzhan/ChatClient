package com.imooc.wangyouzhan.chatclient.previewphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyouzhan on 2016/11/20.
 * Email 936804097@qq.com
 */

public class Imageloader {


    private static Imageloader mInstance = null;

    /**
     * 图片缓存的核心对象
     */
    private LruCache<String, Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;
    private static final int DEAFULT_THREAD_COUNT = 1;

    /**
     * 队列的调度方式
     */
    private Type mType = Type.FIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;

    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;


    /**
     * UI线程中的Handler
     */
    private Handler mUIHandler;


    public enum Type {
        FIFO, FILO;
    }


    public Imageloader(int threadCount, Type type) {
        init(threadCount, type);
    }


    /**
     * 初始化
     *
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {

        //后台轮询线程
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取出一个任务进行执行
                        mThreadPool.execute(getTask());
                    }
                };
            }
        };

        mPoolThread.start();

        //获取我们应用的最大可用的内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        //创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;
    }


    /**
     * 从任务队列取出一个方法
     *
     * @return
     */
    public Runnable getTask() {

        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.FILO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }


    /**
     * 这里判断了两次null,这样做需要做同步的Thread比较少,
     * 可能是一开始的两三个。
     *
     * @return
     */
    public static Imageloader getInstance() {

        if (mInstance == null) {
            synchronized (Imageloader.class) {
                if (mInstance == null) {
                    mInstance = new Imageloader(DEAFULT_THREAD_COUNT, Type.FIFO);
                }
            }
        }
        return mInstance;
    }


    /**
     * 根据path设置图片
     *
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView) {

        imageView.setTag(path);//防止图片混乱
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //获取得到图片,为imageview的回调设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageview = holder.imageView;
                    String path = holder.path;

                    // 将path与getTag存储路径的进行比对
                    if (imageview.getTag().toString().equals(path)) {
                        imageview.setImageBitmap(bm);
                    }
                }
            };
        }

        //根据path在缓存中的获取bitmap
        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            refreashBitmap(bm, path, imageView);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {
                    //加载图片
                    //图片的压缩
                    //1.获得图片需要显示的大小
                    ImageSize imageSize = getImageViewSize(imageView);
                    //2.压缩图片
                    Bitmap bm = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);
                    //3.把图片加入到缓存中
                    addBitmapToLruCache(path, bm);

                    refreashBitmap(bm, path, imageView);

                }
            });
        }
    }

    private void refreashBitmap(Bitmap bm, String path, ImageView imageView) {
        Message message = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bm;
        holder.path = path;
        holder.imageView = imageView;
        message.obj = holder;
        mUIHandler.sendMessage(message);
    }

    /**
     * 把图片加入LruCache
     *
     * @param path
     * @param bm
     */
    private void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null) {

            if (bm != null) {
                mLruCache.put(path, bm);
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        //获得图片的宽和高,并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //使用获得到的InSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }


    /**
     * 根据需求的宽和高以及图片的宽和高计算SampleSize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthRadio = (int) Math.round(width * 1.0 / reqWidth);
            int heightRadio = (int) Math.round(height * 1.0 / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

    /**
     * 根据Imageview获取适当的压缩的宽和高
     *
     * @param imageView
     * @return
     */
    private ImageSize getImageViewSize(ImageView imageView) {

        ImageSize imageSize = new ImageSize();

        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();


        ViewGroup.LayoutParams lp = imageView.getLayoutParams();

        int width = imageView.getWidth();

        if (width <= 0) {
            width = lp.width;//获取imageview在layout中声明的宽度
        }

        if (width <= 0) {
            width = imageView.getMaxWidth();//检查最大值
        }

        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }


        int height = imageView.getHeight();

        if (height <= 0) {
            height = lp.height;//获取imageview在layout中声明的宽度
        }

        if (width <= 0) {
            height = imageView.getMaxHeight();//检查最大值
        }

        if (width <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.width = width;
        imageSize.height = height;

        return imageSize;
    }

    private void addTasks(Runnable runnable) {
        mTaskQueue.add(runnable);
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    /**
     * 根据path在缓存中的获取bitmap
     *
     * @param path
     * @return
     */
    private Bitmap getBitmapFromLruCache(String path) {

        return mLruCache.get(path);
    }


    class ImageSize {
        int width;
        int height;
    }


    private class ImgBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }


}











