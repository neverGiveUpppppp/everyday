package hello.core2.singleton;

public class StatelessService {
/* stateful과 비교
    // private final price;
    public int order(String name, int price) {
//        this.price = price;
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
*/
    public static int order(String name, int price) {
        return price;
    }

}
