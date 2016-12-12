package com.imooc.wangyouzhan.chatclient.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

public class AnimatorActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
            }
        });

        float density = getResources().getDisplayMetrics().density;
        final TranslateAnimation translateAnimation = new TranslateAnimation(imageView.getX(),imageView.getX() + 200,imageView.getY(), imageView.getY() + 300);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView.startAnimation(translateAnimation);

//                ObjectAnimator.ofFloat(imageView,"translationX", imageView.getX(), imageView.getX() + 200).setDuration(2000).start();
//                ObjectAnimator.ofFloat(imageView,"translationY", imageView.getY(), imageView.getY() + 200).setDuration(2000).start();
//                ObjectAnimator.ofFloat(imageView,"rotation",0, 360).setDuration(2000).start();


//                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0, 300);
//                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 0, 300);
//                PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0 , 360);
//                ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3).setDuration(3000).start();

             ObjectAnimator objectAnimator1 =  ObjectAnimator.ofFloat(imageView,"translationX", imageView.getX(), imageView.getX() + 200).setDuration(2000);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView,"translationY", imageView.getY(), imageView.getY() + 200).setDuration(2000);
                objectAnimator2.setInterpolator(new BounceInterpolator());
                ObjectAnimator objectAnimator3 =   ObjectAnimator.ofFloat(imageView,"rotation",0, 360).setDuration(2000);
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3);
//                set.playTogether(objectAnimator1, objectAnimator2, objectAnimator3);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Toast.makeText(getApplicationContext(),"ddddd", Toast.LENGTH_SHORT).show();
                    }
                });
                set.start();

            }
        });




    }
}






















