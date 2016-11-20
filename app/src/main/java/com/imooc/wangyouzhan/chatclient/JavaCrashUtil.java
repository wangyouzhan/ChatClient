package com.imooc.wangyouzhan.chatclient;

/**
 * Created by wangyouzhan on 16/10/13.
 */

public class JavaCrashUtil {


    /**
     * 当应用程序试图在需要对象的地方使用null时,抛出异常。
     *
     * @return
     */
    public static int createNullPointerException() {

        String str = null;
        int length = str.length();

        return length;

    }


    /**
     * 当运算条件出现不符合时,抛出异常
     *
     * @return
     */
    public static int createArithmeticException() {

        int i = 10;
        int j = 0;
        int k = i / j;

        return k;

    }


    /**
     * 使用非法的索引访问出问题的时候抛出异常
     *
     * @return
     */
    public static String createArrayIndexOutOfBoundsException() {
        String[] strArray = {"abc", "def"} ;
        String str01 = strArray[0];
        String str02 = strArray[1];
        String str03 = strArray[2];

        return str03;
    }


    /**
     * 试图将错误类型的对象存储到一个对象数组时抛出的一样
     */
    public static void createArrayStoreException() {
        Object x[] = new String[3];
        x[0] = new Integer(0);
    }


    /**
     * 当试图将对象强制转换为不是实例的子类时,抛出异常
     */
    public static void createClassCastException() {
        Object x = new Integer(0);
        System.out.println((String) x);
    }


}
