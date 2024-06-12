package hello.core4.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String msg) {
        System.out.println("call : " + url + " || msg = " + msg);
    }

    public void disconnect(){
        System.out.println("disconnect : " + url);
    }

    @PostConstruct
     public void init(){
        System.out.println("NetworkClient init : " + url);
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("NetworkClient disconnect : " + url);
        disconnect();
    }


}
