package com.imooc.wangyouzhan.chatclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    EditText ip;
    EditText editText;
    TextView text;
    String ipStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText) findViewById(R.id.ip);
        editText = (EditText) findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.text);

        findViewById(R.id.connection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ipStr = ip.getText().toString();
                connect();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

    }

    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;

    public void connect() {

            AsyncTask<Void,String,Void> read = new AsyncTask<Void, String, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                  try{
                      socket = new Socket(ipStr,51234);
                      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                      publishProgress("@success");
                  }catch (IOException e){

                  }


                   try {
                       String line;
                       while ((line = reader.readLine()) != null){
                            publishProgress(line);
                       }
                   }catch (IOException e){
                       e.printStackTrace();
                   }

                    return null;
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    if (values[0].equals("@success")){
                        Toast.makeText(MainActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
                    }
                    text.append("别人说:" + values[0] + "\n");
                    super.onProgressUpdate(values);
                }
            };
            read.execute();

    }

    public void send() {

        try {
            text.append("我说:" + editText.getText().toString() + "\n");
            writer.write(editText.getText().toString() + "\n");
            writer.flush();
            editText.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
