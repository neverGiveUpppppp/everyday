package com.prac.zPrac1;


class Parent{
    void show(){
        System.out.println("parent");
    }
}
class Child extends Parent{
    void show(){
        System.out.println("child");
    }
}

class Nation{
    String name;
    void setName(String name){
        this.name = name;
    }
}
class Asia extends Nation{
    void food(){
        System.out.println("요리 : 아시아");
    }
    void category(){
        System.out.println("카테고리 : 아시아 음식");
    }
}

class Korea extends Asia{
    void food(){    // 메소드 매개변수에 따라 움직이는 메소드가 달라짐(오버로딩)
        System.out.println("요리 : " + name);
    }
    void food(String foodName){
        System.out.println("요리 : "+ name + " & 요리이름 : "+foodName);
        super.category();   // 카테고리 : 아시아 음식 출력
    }
}
class Japan extends Asia{
    void food(){
        System.out.println("요리 : " + name);
    }
    void food(int ea){
        System.out.println("요리 : "+ name + " & 요리개수 : "+ea);
        super.food();   // 요리 : 아시아 출력
    }
}


public class Inheritance6_BrushUpOn {
    public static void main(String[] args) {
        Parent p = (Parent)new Child(); // Parent class형으로 강제형변환(casting)
        p.show();   // Parent형이던 Child형이든 child 출력됨
        System.out.println("============");

        Asia region = new Asia();
        region.setName("Asia");
        region.food();      // 요리 : 아시아
        System.out.println("============");

        Korea region_kor = new Korea();
        // Asia region 객체에 setName("Asia")으로 넣었지만 인스턴스가 달라 값 공유x
        region_kor.setName("Asia"); // 이 값을 넣기 전에 null로 나오던 부분이 Asia로 다시 잘 나옴
        region_kor.food("된장국");  // 요리 : 한국 & 요리이름 : 된장국
        System.out.println("============");    // 요리 : 일본 & 요리개수 : 1

        Japan region_jap = new Japan();
        region_jap.food(1);

    }
}

