package com.tx.sample;

import com.alibaba.fastjson.JSON;

import java.util.Random;

public class randomNum {



    public static void main(String[] args) {
        StringBuffer randomUnm = new StringBuffer();
        randomUnm.append("{")
                .append(JSON.toJSONString("code")   )
                .append(":")
                .append(JSON.toJSONString(randomNum.GetRandom()))
                .append("}");
        String str ="{\"code\":\"1234\"}";
        System.out.println( str);
        System.out.println(randomUnm);
    }
    public static String GetRandom(){
        StringBuffer randomUnm = new StringBuffer();
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Random random = new Random();
        for(int i=0; i<4;i++) {
            randomUnm.append(ints[random.nextInt(10)]);
        }
        return randomUnm.toString();
    }
}
