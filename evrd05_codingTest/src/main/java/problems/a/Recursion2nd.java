package problems.a;

public class Recursion2nd {

    public static void main(String[] args) {

        String str = "recursion";
        int times = 5;
        recursion(str, times);
    }

    public static void recursion(String value, int times) {
        if (times <= 0) {
            System.out.println("재귀종료");
            return ;
        }
        System.out.println("재귀문 = " + times);
        recursion(value, times - 1);

    }


}
