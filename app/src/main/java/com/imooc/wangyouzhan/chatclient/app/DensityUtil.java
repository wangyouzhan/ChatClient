package com.imooc.wangyouzhan.chatclient.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by wangyouzhan on 16/11/6.
 */

public class DensityUtil {

    private static  float dmDensityDpi = 0.0f;
    private static DisplayMetrics dm;
    private static  float scale = 0.0f;


    public  DensityUtil(Context context){

        dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();

        setDmDensityDpi(dm.densityDpi);
        scale = getDmDensityDpi() / 160;
        Log.i("ddd", toString());


    }


    public static float getDmDensityDpi(){
        return dmDensityDpi;
    }

    public static void setDmDensityDpi(float dmDensityDpi) {
        DensityUtil.dmDensityDpi = dmDensityDpi;
    }


    public   int dip2px(float dipValue){
        return (int)(dipValue *scale + 0.5f);
    }

    public int px2dip(float pxValue){
        return (int)(pxValue / scale + 0.5f);
    }

    @Override
    public String toString() {
        return " dmDensityDpi: " + dmDensityDpi + ":density:" + dm.density + " : width:" + dm.widthPixels + " :height:" + dm.heightPixels;
    }
}
