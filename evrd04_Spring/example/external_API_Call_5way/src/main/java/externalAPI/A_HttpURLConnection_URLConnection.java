package externalAPI;


import java.net.http.HttpClient;

/*

1.HttpURLConnection/URLConnection
http통신을 가능하게 해주는 클래스

특징
자바 기본 API
URL을 이용하여 외부 API에 연결하고 데이터를 전송
데이터의 타입/길이에 거의 제한이 없다.

단점
동기방식 : 응답받을 때까지 스레드가 대기상태
기본 기능만 있음 : 추가기능은 직접 구현해야함


자료 출처
https://jie0025.tistory.com/531


 */
public class A_HttpURLConnection_URLConnection {

    public void get(String requestURL) {
        try{
            HttpClientBuilder.create().build(); //
            HttpClient client = HttpClient.newHttpClient();
            HttpGet getRequest = new HttpGet(requestURL);

        }
    }

}



//
