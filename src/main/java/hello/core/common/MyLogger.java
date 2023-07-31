package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// 프록시 방법 사용 -> 적용 대상이 클래스면 TARGET_CLASS, 인터페이스면 INTERFACES
// 가짜 프록시 객체 생성해서 spring 이 등록한다.
// CGLIB 사용하여 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다.
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]"+"["+requestURL+"] " +message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"]"+" request scope bean create: "+this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request scope bean close: "+this);
    }
}
