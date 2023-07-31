package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 빈의 이벤트 라이프사이클
 * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 초기화 콜백 -> 사용
 * 소멸 전 콜백 -> 스프링 종료
 *
 * 객체의 생성과 초기화를 분리하는 것이 좋다.
 *
 * 스프링 빈의 생명주기 콜백 지원
 * 1. 인터페이스 (InitializingBean, DisposableBean) -> 일회용 Bean ( 거의 사용하지 않음 )
 * 2. 설정 정보에 초기화 메서드, 종료 메서드 지정
 * 3. @PostConstruct, @PreDestory 애노테이션 지원
 *
 * '초기화, 소멸 인터페이스 단점'
 * - 스프링 전용 인터페이스로 스프링에 의존적
 * - 이름 변경할 수 없음
 * - 외부 라이브러리에 적용할 수 없음
 */
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ConfigurableApplicationContext, AnnotationConfigApplicationContext
        // 기본 ApplicationContext 는 close 제공하지 않음
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
