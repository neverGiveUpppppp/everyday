package com.prac.zPrac1;


class Food1{
    String foodName;

    void setFoodName(String foodName){
        this.foodName = foodName;
    }
}

class Soup1 extends Food1{
    void riceEtc(){
        System.out.println(this.foodName + "에는 잡곡밥"); // 부모 기능 확장 : 오버라이딩(재정의)
    }
}

public class Inheritance1 {
    public static void main(String[] args) {
        Soup1 rice = new Soup1();
        // 부모 기능만 사용
        rice.setFoodName("한식");
        System.out.println(rice.foodName); // 한식
        // 부모 기능 확장 : 오버라이딩(재정의)
        rice.riceEtc();     // 한식에는 잡곡밥

    }
}


