package com.example.externalapi;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class A_HttpClient {

    public void get(String requestURL) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(requestURL);
            getRequest.addHeader("x-api-key", RestTestCommon.API_KEY); // key input

            HttpResponse response = client.execute(getRequest);

            // Response
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response error : " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


}
