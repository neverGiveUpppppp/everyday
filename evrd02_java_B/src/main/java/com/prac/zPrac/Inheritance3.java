package com.prac.zPrac;



class Food3{
    String food;
    void setFood(String food){
        this.food = food;
    }
    
    void printFood(){
        System.out.println("SuperClass Food");
    }
    
}

class Soup3 extends Food3{
    void cooking(){
        System.out.println(this.food + "이 끊고 있습니다");
    }
}

class SpecialSoup extends Soup3{
    void cooking(String str) {
        System.out.println(str+" "+this.food + "이 끊고 있습니다");
    }
}


public class Inheritance3 {
    public static void main(String[] args) {
        Soup3 soup = new Soup3();
        soup.setFood("미역국");
        System.out.println(soup.food); // 미역국 : Food class의 필드값 가져와서 프린트 찍은 것
        soup.printFood();
        // 부모 기능 확장 : 오버라이딩
        soup.cooking();         // Soup3 클래스 cooking()

        // 
        SpecialSoup ss = new SpecialSoup();
        // SpecialSoup의 상위 클래스인 Soup3에서 미역국이라고 값을 최상위인 Food class의 food변수에 넣어줬는데
        // SpecialSoup클래스에서는 null로 나옴. 값 공유x 
        // 값 공유는 객체의 주소를 따라가는 듯함
        ss.setFood("된장국");
        ss.cooking("특별한"); // SpecialSoup 클래스 cooking()

    }
}
