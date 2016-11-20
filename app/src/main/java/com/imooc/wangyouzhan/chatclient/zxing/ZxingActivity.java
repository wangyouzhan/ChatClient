/**
 *
 */
package com.imooc.wangyouzhan.chatclient.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;
import com.imooc.wangyouzhan.zxing_lib.CaptureActivity;
import com.imooc.wangyouzhan.zxing_lib.Intents;

import java.io.File;

/**
 * @author yumin
 */
public class ZxingActivity extends Activity {

    /**
     *
     */
    private static final int REQUEST_CODE = 200;

    private EditText et_content;
    private Button btn_create_qr;
    private CheckBox cb_opetion;
    private ImageView img_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_main);
        initActivity();

        Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        handler.sendEmptyMessage(1);




    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (null != data && requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    data.setClass(this, CaptureResultActivity.class);
                    startActivity(data);
                    break;
                default:
                    break;
            }
        }
    }

    private void initActivity() {

        et_content = (EditText) findViewById(R.id.et_content);
        btn_create_qr = (Button) findViewById(R.id.btn_create_qr);
        cb_opetion = (CheckBox) findViewById(R.id.cb_opetion);
        img_view = (ImageView) findViewById(R.id.img_view);
        btn_create_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String filePath = getFileRoot(ZxingActivity.this) + File.separator
                        + "qr" + System.currentTimeMillis() + ".jpg";


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean success = QRCodeUtil.createQRImage(
                                et_content.getText().toString().trim(),
                                800, 800,
                                cb_opetion.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.haizewang_23): null,filePath);

                        if (success){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img_view.setImageBitmap(BitmapFactory.decodeFile(filePath));
                                }
                            });
                        }


                    }
                }).start();


            }
        });


        Button btnScan = (Button) findViewById(R.id.scan);
        btnScan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCapture(null);
            }
        });
        Button btnScanGbk = (Button) findViewById(R.id.scan_gbk);
        btnScanGbk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCapture("GBK");
            }
        });
        Button btnScanUtf8 = (Button) findViewById(R.id.scan_utf_8);
        btnScanUtf8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCapture("UTF-8");
            }
        });

    }


    private String getFileRoot(Context context){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }

        }
        return context.getFilesDir().getAbsolutePath();
    }

    private void callCapture(String characterSet) {


        Intent intent = new Intent();
        intent.setAction(Intents.Scan.ACTION);
        // intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
        intent.putExtra(Intents.Scan.CHARACTER_SET, characterSet);
        intent.putExtra(Intents.Scan.WIDTH, 800);
        intent.putExtra(Intents.Scan.HEIGHT, 600);
        // intent.putExtra(Intents.Scan.PROMPT_MESSAGE, "type your prompt message");
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
