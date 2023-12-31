package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = "+url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : "+url);

    }

    public void call(String message) {
        System.out.println("call : "+url + ", message : " +message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close : "+url);
    }

    /**
     * 초기화, 소멸 인터페이스 사용
    @Override
    public void afterPropertiesSet() throws Exception {
        // 의존관계 주입 후 호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
     */

    @PostConstruct
    public void init() throws Exception {
        // 의존관계 주입 후 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
