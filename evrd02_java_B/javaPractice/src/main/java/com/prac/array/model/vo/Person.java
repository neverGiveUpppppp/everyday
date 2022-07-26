package com.prac.array.model.vo;

//객체 배열

public class Person {

    // 패키지 이름처럼 MVC패턴의 model
    // 즉, 데이터 관련
    private String name;
    private int age;
    private char gender;
    private double height;
    private double weight;

    public Person() {}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(String name, int age, char gender, double height, double weight) {
        this(name,age); 		// this() 생성자
//		this.name = name;
//		this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

    }


    // setter
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }


    // getter
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public char getGender() {
        return gender;
    }
    public double getHeight() {
        return height;
    }
    public double getWeight() {
        return weight;
    }

    public String personInfo() {
        return name + ", " + age + ", " + gender + ", " + height + ", " + weight;
    } // 위의 메소드에서 void 넣고 println을 썼다면 틀린 것

}
