package com.imooc.wangyouzhan.chatclient.generic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

public class GenricActivity extends AppCompatActivity {

    private static final String TAG = "GenricActivity";

    private ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genric);
//        imageView = (ImageView) findViewById(R.id.imageview);


//        bitmap  = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_app);
//        imageView.setImageBitmap(bitmap);


//        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Matrix matrix = imageView.getMatrix();
//                matrix.postRotate(49f);
//                Drawable drawable = imageView.getDrawable();
//
//                 bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(),
//
//                        drawable.getIntrinsicHeight(),
//
//                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
//
//                                : Bitmap.Config.RGB_565);
//
//                Canvas canvas = new Canvas(bitmap);
//
//
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//                drawable.draw(canvas);
//
//                Bitmap bitmaptwo = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
//                imageView.setImageBitmap(bitmaptwo);
////                imageView.setImageMatrix(matrix);
////                imageView.invalidate();
//
//            }
//        });


    }
}





























