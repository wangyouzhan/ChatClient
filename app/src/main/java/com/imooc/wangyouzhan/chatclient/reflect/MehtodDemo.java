package com.imooc.wangyouzhan.chatclient.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangyouzhan on 2016/12/17.
 * Email 936804097@qq.com
 */

public class MehtodDemo {



    public static void main(String[] args) throws NoSuchMethodException {


        A a = new A();
        Class c = a.getClass();

        Method method = c.getMethod("print",new Class[]{int.class, int.class});

        try {
            Object  obj = method.invoke(a, new Object[]{10, 20});

            Method m1 = c.getMethod("print", String.class, String.class);

            m1.invoke(a, "wang", "you");


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}



class A{
    public void  print(int a, int b){
        System.out.println(a + b);
    }

    public void print(String a, String b){
        System.out.println(a.toUpperCase() + b.toLowerCase());
    }

}
