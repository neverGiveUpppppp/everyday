package com.prac.localDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSummary {


    public void localDateTime(){
        // 출처 : https://covenant.tistory.com/255

        // 1. LocalDateTime now() - 현재 시스템 시간
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 2. LocalDateTime 타입 생성
//		    초, 밀리세컨드는 생략가능
//		    month는 int 뿐만 아니라, enum형(java.time.Month) 사용가능
        LocalDateTime.of(2021, 1, 1, 0, 0, 0); // 2021-01-01T00:00

        // 3. DateTimeFormatter.ofPattern() : 출력형식 변환
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        String localDateTimeFormat1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String localDateTimeFormat2 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println(localDateTimeFormat1);
        System.out.println(localDateTimeFormat2);

        // 4. LocalDateTime 시간요일, 조회
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println("getYear() = " + now1.getYear());
        System.out.println("getMonth() = " + now1.getMonth());
        System.out.println("getDayOfMonth() = " + now1.getDayOfMonth());
        System.out.println("getDayOfWeek() = " + now1.getDayOfWeek());
        System.out.println("getDayOfYear() = " + now1.getDayOfYear());

        System.out.println("getHour() = " + now.getHour());
        System.out.println("getMinute() = " + now.getMinute());
        System.out.println("getSecond() = " + now.getSecond());
        System.out.println("getNano() = " + now.getNano());

        // 5. LocalDateTime 연산 : Plus & Minus
//		     인자만큼 시간요일 +-
        LocalDateTime time = LocalDateTime.of(2021, 9, 9, 10, 0, 0);
        System.out.println("plusYears(3) = " + time.plusYears(3));
        System.out.println("plusMonths(3) = " + time.plusMonths(3));
        System.out.println("plusDays(3) = " + time.plusDays(3));
        System.out.println("plusHours(3) = " + time.plusHours(3));
        System.out.println("plusMinutes(3) = " + time.plusMinutes(3));
        System.out.println("plusSeconds(3) = " + time.plusSeconds(3));

        System.out.println("minusYears(3) = " + now.minusYears(3));
        System.out.println("minusMonths(3) = " + now.minusMonths(3));


        // 6. LocalDateTime 비교
//			isAfter() - 인자보다 미래 시간이라면 true 반환
//			isBefore() - 인자보다 과거 시간이면 true 반환
//			isEqual() - 인자와 같은 시간이면 true 반환
//			compareTo()
//			compareTo() > 0 : 인자보다 미래 시간
//			compareTo() < 0 : 인자보다 과거 시간
//			compareTo() == : 인자와 같은 시간
        LocalDateTime compare1 = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime compare2 = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime compare3 = LocalDateTime.of(2023, 1, 1, 0, 0, 0);

        if (compare1.isBefore(compare3)) // isBefore()
            System.out.println("compare1 is before compare3");

        if (compare3.isAfter(compare1))  // isAfter()
            System.out.println("compare3 is after compare1");

        if (compare1.isEqual(compare2))  // isEqual()
            System.out.println("compare1 is equal to compare2");

        if (compare1.compareTo(compare2) == 0) // compareTo()
            System.out.println("compare1 is equal to compare2");

        // 7.LocalDateTime → LocalDate로 변환
//		시간 단위(시간, 분, 초, 나노세컨드)가 없는 LocalDate로 반환
        LocalDateTime convert = LocalDateTime.now();
        System.out.println(convert);				// 2021-11-08T12:42:11.769062
        System.out.println(convert.toLocalDate());  // 2021-11-08
    }

}
