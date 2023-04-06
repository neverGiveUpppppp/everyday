package com.prac.zPrac;


class Phone{
    String type;
    String phoneName;

    void setType(String type){
        this.type = type;
    }
    void setPhoneName(String phoneName){
        this.phoneName = phoneName;
    }
}

class SmartPhone extends Phone{
    void specification(){
        System.out.println("스마트폰명 : " + this.type + "\n스마트폰 기종 : "+this.phoneName);
    }
    String application(){
        System.out.println("스마트폰 앱");
        return "앱 작동";
    }
    String application(String str){
        System.out.println("스마트폰 앱 : " + str);
        return str + " 앱 작동";
    }

}

class Galaxy extends SmartPhone{
    String samsungPay(){
        System.out.println(this.type+" 삼성페이");
        return this.phoneName+" 페이 작동";
    }
    String application(String str){
        System.out.println("스마트폰 앱 갤럭시 : " + str);
        return str + " 갤럭시 앱 작동";
    }
    String application(int num){
        System.out.println("스마트폰 앱 갤럭시 : " + num + "개");
        return "갤럭시 앱 "+ num + "개 작동";
    }
    String application(int num1, int num2){
        return "갤럭시앱 "+ num1 + "개 중 " +num2+"개 작동";
    }
}




public class Inheritance4_1 {
    public static void main(String[] args) {
        SmartPhone smartPhone = new SmartPhone();
        smartPhone.setType("galaxy");
        smartPhone.setPhoneName("galaxy10");
//        System.out.println(smartPhone.type);
//        System.out.println(smartPhone.phoneName);
//
        smartPhone.application();           // 매개변수 non
        smartPhone.application("캘린더"); // 매개변수 오버로딩
//
//        smartPhone.specification(); // 부모클래스의 멤버변수를 받아 print

        Galaxy galaxy = new Galaxy();
//        galaxy.setType("galaxy note");
//        galaxy.setPhoneName("galaxy note 20");
//        galaxy.samsungPay();
//        System.out.println(galaxy.samsungPay());
        galaxy.application();           // 기본생성자가 없는 Galaxy class이기에 부모인 SmartPhone class의 기본생성자 app()가 실행됨
        galaxy.application("크롬");
        galaxy.application(5);
        System.out.println(galaxy.application(5,5));
    }


}
