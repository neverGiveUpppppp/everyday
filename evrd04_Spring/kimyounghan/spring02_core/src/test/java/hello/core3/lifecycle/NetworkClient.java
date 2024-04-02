package hello.core3.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String msg){
        System.out.println("call : " + url + " || msg = " + msg);
    }

    public void disConnect(){
        System.out.println("close : " + url);
    }


    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disConnect();
    }

}
