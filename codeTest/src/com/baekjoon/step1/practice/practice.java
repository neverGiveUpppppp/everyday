package com.baekjoon.step1.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class practice {
    public static void main(String[] args) throws IOException {
//        int a = 0;
//        int b = 0;
//
//        Scanner sc = new Scanner(System.in);
//        a = sc.nextInt();
//        b = sc.nextInt();
//
//        System.out.println(a-b);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int[] arr1 = {1,1,2,2,2,8};
        String answer = "";
        int user = 0;

        for(int i=0; i < 6; i++){
            user = Integer.parseInt(st.nextToken()); // NoSuchElementException 발생 : 참조 https://help.acmicpc.net/judge/rte/NoSuchElement
            answer += arr1[i] - user+ " ";
        }

        System.out.println(answer);

    }
}
