package com.imooc.wangyouzhan.chatclient.view;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

public class ScrollerViewActivity extends AppCompatActivity {

    private static final String TAG = "ScrollerViewActivity";
    private Button btn_button;
    private ViewButton view_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_view);


        btn_button = (Button) findViewById(R.id.btn_button);
        view_button = (ViewButton) findViewById(R.id.view_button);
        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_button.smoothScrollTo((int) view_button.getX() + 400, (int) view_button.getY());
            }
        });

//        valueAnimatorView();
//        handerViewScroller();

    }


    private void valueAnimatorView() {
        final int startX = 0;
        final int deltaX = 100;

        final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animator.getAnimatedFraction();
                Log.d(TAG, "onAnimationUpdate: ---------------------" + fraction);
//                btn_button.scrollTo(startX + (int)(deltaX * fraction), 0);
//                btn_button.scrollTo(startX + (int)(deltaX * fraction), 0);
                btn_button.scrollBy(startX + (int) (deltaX * fraction), 0);
            }
        });

        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
                Toast.makeText(ScrollerViewActivity.this, "ddd", Toast.LENGTH_SHORT).show();
                ;
            }
        });
    }


    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELATED_TIME = 33;
    private int mCount = 0;
    private Handler handler;


    private void handerViewScroller() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {

                    case MESSAGE_SCROLL_TO:
                        mCount++;
                        if (mCount <= FRAME_COUNT) {
                            float fraction = mCount / (float) FRAME_COUNT;
                            int scrollX = (int) (fraction * 100);
                            btn_button.scrollTo(scrollX, 0);
                            handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELATED_TIME);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, 1000);
            }
        });


    }


}

























