package com.imooc.wangyouzhan.chatclient.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wangyouzhan on 16/11/6.
 */

public class OKHttpUtil {


    public  static  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    static {
        client.newBuilder().connectTimeout(30, TimeUnit.SECONDS);
    }


    /**
     * 同步请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String run(String url) throws IOException{

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else{
            throw new IOException("Unexpected code" + response);
        }
    }


    /**
     * post 请求
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public String post(String url, String json) throws  IOException{

        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else{
            throw  new IOException("Unexpected code  " + response);
        }
    }

    public static Response execute(Request request) throws IOException{
        return client.newCall(request).execute();
    }

    /**
     * 异步get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String getStringFromServer(String url){

        try{
            Request request = new Request.Builder().url(url).build();
            Response response = execute(request);
            if (response.isSuccessful()) {
                String responseUrl = response.body().string();



                return responseUrl;
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }catch (Exception o){
            o.printStackTrace();
        }





        return "";
    }


    /**
     * 异步队列请求
     * @param url
     * @param callback
     */
    public static void asyRequestTask(String url, Callback callback){
        try{
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(callback);
        }catch (Exception o){
            o.printStackTrace();
        }
    }



}
