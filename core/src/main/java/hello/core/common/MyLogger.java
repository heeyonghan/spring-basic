package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Scope(value = "request") // request 지정으로 HTTP 요청 당 하나씩 생성되고 끝나는 시점에 소멸됨.
// request 요청 없이도 가짜를 주입하여 코드 테스트 가능.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]["+requestURL+"] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request bean create : "+this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request bean close : "+this);
    }
}
