package com.prac.temp;

import java.util.Arrays;

public class Test22{
    public static int[] arr(int[] a){
        a[0] = 50;
        return a;
    }

    public static void main(String[] args){
        int n[] = {1,2};
        arr(n);
    }

}
