package com.prac.lambda;





// 1. 두개의 숫자 더하기
// https://coding-factory.tistory.com/265

interface Compare{
    public int compareTo(int a, int b);
}
public class Lambda_EX1{
    public static void exec(Compare compare){
        int k = 10;
        int m = 20;
        int value = compare.compareTo(k, m);
        System.out.println(value);
    }

    public static void main(String[] args) {
        exec((i,j) -> {
            return i + j; // 리턴값 30
        });
    }
}
