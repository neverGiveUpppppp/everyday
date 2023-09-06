package hello.predo.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " || price" + price);
        this.price = price; // 상태 설계 문제인 부분
    }

    public int getPrice(){
        return price;
    }

}
