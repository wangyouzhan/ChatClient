package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.imooc.wangyouzhan.chatclient.R;

public class AnimationActivity extends AppCompatActivity {


    private ImageView img_view;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        listview = (ListView) findViewById(R.id.listview);

        img_view = (ImageView) findViewById(R.id.img_view);
        Animation animation =  AnimationUtils.loadAnimation(this,R.anim.alpha);
//        img_view.startAnimation(animation);

        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
//        img_view.startAnimation(scaleAnimation);

        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
//        img_view.startAnimation(translate);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translate);
        img_view.startAnimation(animationSet);


        String[] strs = new String[] {
                "first", "second", "third", "fourth", "fifth"
        };
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs));

        Animation animationSet1 = AnimationUtils.loadAnimation(this,R.anim.zoom);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animationSet1);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listview.setLayoutAnimation(layoutAnimationController);
        listview.startLayoutAnimation();




    }
}

























