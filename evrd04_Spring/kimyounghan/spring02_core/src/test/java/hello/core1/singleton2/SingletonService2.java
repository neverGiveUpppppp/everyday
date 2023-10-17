package hello.core1.singleton2;

public class SingletonService2 {
    
    private final SingletonService2 instance = new SingletonService2();
    
    public SingletonService2 getInstance(){
        return instance;
    }
    
    public SingletonService2(){
        
    }
    
    public void logic(){
        System.out.println("싱글톤 연습");
    }
    
}
