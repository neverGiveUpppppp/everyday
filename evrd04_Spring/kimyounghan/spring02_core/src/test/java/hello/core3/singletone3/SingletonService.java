package hello.core3.singletone3;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
        
    }
    
    public void logic(){
        System.out.println("싱글톤패턴 연습");
    }


}
