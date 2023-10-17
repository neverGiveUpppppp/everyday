package hello.core1.singleton2;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

}
