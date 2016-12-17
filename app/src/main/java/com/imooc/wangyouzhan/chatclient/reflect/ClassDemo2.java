package com.imooc.wangyouzhan.chatclient.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wangyouzhan on 2016/12/17.
 * Email 936804097@qq.com
 */

public class ClassDemo2 {


    public static void main(String[] args){

        Class c1 = int.class;
        Class c2 = String.class;
        Class c3 = double.class;
        Class c4 = Foo.class;


//        System.out.println(c1.getName());
//        System.out.println(c2.getSimpleName());
        System.out.println(c4.getCanonicalName());


        Method[] methods = c4.getMethods();

        for (int i = 0; i < methods.length; i++)
        {
            Class returnType = methods[i].getReturnType();

            System.out.print(returnType.getName() + " " + methods[i].getName() + "( ");


            Class[] paramTypes = methods[i].getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++){

                System.out.print(paramTypes[j].getName() + " ");
            }
            System.out.println(" ) ");
        }


        System.out.println("------------------------------");

        Field[] fs = c4.getDeclaredFields();


        for(Field field : fs){

            Class fieldTypes = field.getType();
            String typeName = fieldTypes.getName();
            String fileName = field.getName();
            int modifier = field.getModifiers();
            System.out.println(modifier + "  " + typeName + "  " + fileName);
        }


        Constructor[] constructors = c4.getDeclaredConstructors();
        for(Constructor constructor : constructors){

            System.out.println(constructor.getName());

        }



    }

}
