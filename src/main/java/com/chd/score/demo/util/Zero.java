package com.chd.score.demo.util;

public class Zero {
    public static String zeroAddzdy(String string,int data){
        String newString = "";
        String zero = "";
        int strlength = string.length();
        // System.out.println(strlength);
        if(strlength >= data){
            newString = string.substring(strlength-data,strlength);
        }else{
            int i = data - strlength;
            for (int j = 0; j < i; j++) {
                zero = zero + "0";
            }
            newString = zero + string;
        }
        return newString;
    }
}
