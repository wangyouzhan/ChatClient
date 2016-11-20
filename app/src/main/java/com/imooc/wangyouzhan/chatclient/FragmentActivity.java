package com.imooc.wangyouzhan.chatclient;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.app.ExampleApplication;
import com.squareup.leakcanary.RefWatcher;

public class FragmentActivity extends AppCompatActivity  {


    class  Leak{

    }

    private static  Leak mLeak;

    private ImageView frag_add;
    private Handler mHandler;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mLeak = new Leak();

        RefWatcher refWatcher = ExampleApplication.getRefWatcher(FragmentActivity.this);
        refWatcher.watch(mLeak);

        frag_add = (ImageView)findViewById(R.id.img_button);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                animationTransition();
//                animationTransitionValueObject();

                //Tween Animation
                TweenAnimation();




            }
        });



    }


    private  void  TweenAnimation(){

        Animation animation = new TranslateAnimation(0,200, 0, 200);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        frag_add.startAnimation(animation);

    }


    private void animationTransitionValueObject(){

        float fromX = frag_add.getTranslationX();

        ObjectAnimator oa = ObjectAnimator.ofFloat(frag_add, "translationX",fromX, fromX + 100,fromX);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(frag_add,"scaleX",0,1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(frag_add, "scaleY",0,1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(frag_add, "rotation", 0, 360);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(frag_add, "alpha", 1, 0);
        AnimatorSet as = new AnimatorSet();

        oa.setInterpolator(new BounceInterpolator());
        as.play(oa).with(scaleX).with(scaleY).with(rotation);

//        as.play(alpha).with(rotation);
        as.setDuration(6000);
        as.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                frag_add.setAlpha(1.0f);
            }
        });

        as.start();


    }


    public void animationTransition(){


        float fromX = frag_add.getTranslationX();
        ValueAnimator va = ValueAnimator.ofFloat(fromX, fromX + 100, fromX);
        va.setDuration(6000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float)animation.getAnimatedValue();
                frag_add.setTranslationX(v);
                Log.d("ta", "v---:" + v);

            }
        });
        va.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }
}
