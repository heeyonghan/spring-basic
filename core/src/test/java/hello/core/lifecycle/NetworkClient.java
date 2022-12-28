package hello.core.lifecycle;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 : " + url);
        connect();
        call("생성자 호출 완료");

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
}
