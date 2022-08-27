package com.prac01.collection.set.model.vo;

public class Dog {

    // default constructor
    private String name;
    private double weight;

    public Dog() {}
    public Dog(String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    
    // getter & setter
    public void setName(String name){
        this.name= name;
    }
    public String getName(){
        return name;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }

    // toString
    @Override
    public String toString(){
        return name + " " + weight+"kg";
    }

    // equals()
//    public boolean equals(Object obj){
//
//        // 1.클래스 비교
//        if(this == obj){
//            return true;
//        }
//        if(obj == null){
//            return false;
//        }
//
//        if(getClass() != obj.getClass()){
//            return false;
//        }
//
//
//        // 2.내용 비교
//        Dog other = (Dog)obj; // (Dog) 다운캐스팅
//        if(name == null){
//            if(other.name != null){
//                return false;
//            }
//        }else if(!name.equals(other.name)){
//            return false;
//        }
//
//        if(weight != other.weight){
//            return false;
//        }
//        return true;
//    }


    @Override
    public int hashCode() {
        final int PRIME = 31; // 컴퓨터가 이해하기 좋은 숫자가 31이라함
        int result = 1;

        result = PRIME * result + (name == null ? 0 : name.hashCode()); // 내 해쉬코드가 아니라 스트링의 해쉬코드를 가져오는 것
        result = PRIME * result + (int)weight; // 형이 안맞아서 에러나니 캐스팅 또는 소수점 없게끔 계산

        return result;
    }


//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }


}
