package problems.a;

import java.io.*;

public class Practice {
    public static void main(String[] args) throws IOException {

/*
값 출력하기 : 4가지방법
    1.BufferedWriter
    2.****StringBuilder****
    3.StringBuffer
    4.System.out.print()
 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        bw.write("practice");
//        bw.newLine();
//        bw.write("newLine test");
//        bw.flush();
//        bw.close();
//        System.out.println(br);

        StringBuilder sb = new StringBuilder();
        String answer = sb.append("스트링빌더").toString();
        sb.append(4);
        System.out.println(answer.toString()); // 스트링빌더

        sb.append("스트링빌더");
        System.out.println(sb.toString()); // 스트링빌더4스트링빌더

        sb.insert(5, "  ");
        System.out.println(sb.toString()); // 스트링빌더  4스트링빌더

        sb.replace(5, 8, "---"); // 스트링빌더---스트링빌더
        System.out.println(sb.toString());

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());  // 스트링빌더---스트링빌

        sb.reverse();
        System.out.println(sb.toString());  // 빌링트스---더빌링트스

/*
문자열 반복하는 방법 3가지
    1.String.repeat()
    2.StringBuilder.append() + for문
    3.재귀함수
 */

// 1.String.repeat()
        String str = "hell";
        int a = 5;
        String repeat = str.repeat(a);
        System.out.println(repeat); // hellhellhellhellhell

        String repeat2 = str.repeat(3);
        System.out.println(repeat2); // hellhellhell

// 2.StringBuilder.append() + for문
        StringBuilder sbb = new StringBuilder();
        for(int i = 0; i < a; i++)
            sbb.append(str);
        System.out.println(sbb.toString()); // hellhellhellhellhell

// 3.재귀함수
        class Recursion{
            public static void main(String[] args) {
                String str = "hi";
                int times = 5;

                repeatRecursion(str, times);
            }
            public static void repeatRecursion(String str,  int count){
                if(count <= 0)
                    return;
                System.out.println("재귀호출 : "+str);

                // 재귀 반복 호출
                repeatRecursion(str, count);
            }
        }
    }
}
