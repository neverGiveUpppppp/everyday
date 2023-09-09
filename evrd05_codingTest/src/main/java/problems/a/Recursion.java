package problems.a;

public class Recursion {

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
