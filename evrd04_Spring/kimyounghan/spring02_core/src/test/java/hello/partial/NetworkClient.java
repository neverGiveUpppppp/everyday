package hello.partial;

public class NetworkClient {

    private String url;


    // Constructor
    public NetworkClient(){
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }


    // 서비스 시작 시, 호출
    public void connect(){
        System.out.println("url = " + url);
    }
    public void call(String message){
        System.out.println("call : " + url  + " || message = " + message);
    }


    // 서비스 종료 시, 호출 for 안전하게 연결이 끊어지도록
    public void disconnect(){
        System.out.println("close : " + url);
    }
}
