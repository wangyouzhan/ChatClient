package com.imooc.wangyouzhan.chatclient;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    public void caculateArray(){
        int sum = 14;
        int[] arr = {1,5,3,8,9,6,2,8};
        Arrays.sort(arr);


        for(int i = 0, j = arr.length -1; i < j;){
            if(arr[i] + arr[j] == sum)
            {
                System.out.println(arr[i] + "+" + arr[j]);
                i++;
            }
            else if(arr[i] + arr[j] < sum){
                i++;
            }
            else{
                j--;
            }
        }
    }

}