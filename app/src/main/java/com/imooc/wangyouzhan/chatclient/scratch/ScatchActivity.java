package com.imooc.wangyouzhan.chatclient.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

public class ScatchActivity extends AppCompatActivity {


    private GuaGuaKa id_guaguaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatch);



        id_guaguaka = (GuaGuaKa) findViewById(R.id.id_guaguaka);
        id_guaguaka.setOnGuaGuaKaCompleteListener(new GuaGuaKa.OnGuaGuaKaCompleteListener() {
            @Override
            public void complete() {
                Toast.makeText(ScatchActivity.this,"用户已经刮得差不多了", Toast.LENGTH_SHORT).show();
            }
        });

        id_guaguaka.setmText("Android新技能");

    }





}











































