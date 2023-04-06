package com.prac.zPrac;



interface HairWork{
    String hairWorking();
    String waiting();
}

class Customer {
    public String customerType;

    void setCustomerType(String customerType){
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }
}

class HairCut extends Customer implements HairWork{
    public String hairWorking(){
        return "Haircut";
    }

    @Override
    public String waiting() {
        System.out.println("기다리는 중");
        return "기다리는 중";
    }
}

class Perm extends Customer implements HairWork {
    public String hairWorking(){
        return "Perm";
    }
    public String waiting() {
        System.out.println("기다리는 중");
        return "기다리는 중";
    }
}

class Hairdresser{
    void hairJob(HairWork hairWork){
        System.out.println("머리 시작 " + hairWork.hairWorking());
    }
    void hairJob(Perm perm){
        System.out.println("머리 시작 : " + perm.hairWorking());
    }
    String waiting(HairWork hairWork){
        System.out.println("손님 쇼파에서 대기하도록 안내시작 ");
        return hairWork.waiting();
    }
    
}


public class Interface2 {
    public static void main(String[] args) {
        Hairdresser hairdresser = new Hairdresser();
        HairCut hairCut = new HairCut();
        Perm perm = new Perm();
        hairCut.setCustomerType("남성");
        perm.setCustomerType("여성");
//        hairCut.customerType
        hairdresser.hairJob(hairCut);
        hairdresser.hairJob(perm);
        hairdresser.waiting(hairCut);
        hairdresser.waiting(perm);
    }

}
