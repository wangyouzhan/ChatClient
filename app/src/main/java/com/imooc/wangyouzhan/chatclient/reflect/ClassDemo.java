package com.imooc.wangyouzhan.chatclient.reflect;

/**
 * Created by wangyouzhan on 2016/12/17.
 * Email 936804097@qq.com
 */

public class ClassDemo {



    public  static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


                Class c = Class.forName("com.imooc.wangyouzhan.chatclient.reflect.Foo");

                Apple apple = (Apple)c.newInstance();

                apple.info();





    }

}

























