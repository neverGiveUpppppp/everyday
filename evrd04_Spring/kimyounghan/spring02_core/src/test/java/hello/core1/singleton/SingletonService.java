package hello.core.singleton;


/*

<싱글톤 패턴(Singleton Pattern)>
클래스 인스턴스가 딱1개만 생성됨을 보장하는 디자인패턴
    인스턴스 2개 이상 생성방지 필수
    → private 생성자로 외부 new키워드 생성을 방지
값 조회 시는 getInstance()만 가능

 */
public class SingletonService {

    // 1.static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // 2.public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록
    public static SingletonService getInstance() {
        return instance;
    }

    // 3.생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
