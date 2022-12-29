package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 : " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String msg) {
        System.out.println("msg = " + msg);
    }

    public void disconnect() {
        System.out.println("disconnect = " + url);
    }

    @PostConstruct
    public void init() {
        connect();
        call("생성자 호출 완료");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }
}
