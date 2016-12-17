package com.imooc.wangyouzhan.chatclient.reflect;

/**
 * Created by wangyouzhan on 2016/12/17.
 * Email 936804097@qq.com
 */

public class Foo implements Apple{


    private boolean flag = true;
    public  long count = 2;

    public Foo(int name){
        
    }


    @Override
    public void info() {
        System.out.println("apple...foo....");
    }


    public long getInfo(){
        return 2l;
    }

    private void getwang(){

    }
}
