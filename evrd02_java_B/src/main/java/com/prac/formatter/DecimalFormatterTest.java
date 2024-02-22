package com.prac.formatter;

import java.text.DecimalFormat;

public class DecimalFormatterTest {

    public static void main(String[] args) {
        String visitors1 = "12345";
        String visitors2 = "123456789000000000";

        DecimalFormat formatter = new DecimalFormat();
        long number1 = Long.parseLong(visitors1); // 자동으로 3칸마다 , 추가
        long number2 = Long.parseLong(visitors2);
        String formattedNum1 = formatter.format(number1);
        String formattedNum2 = formatter.format(number2);

        System.out.println(formattedNum1); // 12,345
        System.out.println(formattedNum2); // 123,456,789,000,000,000
    }

}
