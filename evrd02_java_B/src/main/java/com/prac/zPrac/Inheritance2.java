package com.prac.zPrac;


class Food2{
    String foodName;

    void setFoodName(String foodName){
        this.foodName = foodName;
    }
}

class Soup2 extends Food2{
    void voiled(){
        System.out.println(this.foodName+" soup is boiled");
    }

}


class SeaSoup extends Soup2{
    void voiled(int min){
        System.out.println(this.foodName + " soup is boiled for "+ min +"mins");
    }
}


public class Inheritance2 {
    public static void main(String[] args) {
        SeaSoup seaSoup = new SeaSoup();
        seaSoup.setFoodName("seaweed");
        seaSoup.voiled();        // seaweed soup is boiled
        seaSoup.voiled(30); // seaweed soup is boiled for 30mins
        // 부모클래스 기능을 그냥 가져다 쓰기도 하고(31라인)
        // 자식에서 오버라이딩(재정의)해서 사용한 예제(32라인)

    }
}

/*
    점프투자바
    상속,생성자, 인터페이스, 추상클래스
    예제 전부 안보고 코드 짤 수 있도록 연습
    +@ void 대신 return 값 주고 받으면서 뭔가 만들어보기


 */