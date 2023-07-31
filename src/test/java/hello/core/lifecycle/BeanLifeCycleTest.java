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
 * 3. @PostConstruct, @PreDestroy 애노테이션 지원 (가장 추천)
 *
 * '초기화, 소멸 인터페이스 단점'
 * - 스프링 전용 인터페이스로 스프링에 의존적
 * - 이름 변경할 수 없음
 * - 외부 라이브러리에 적용할 수 없음
 *
 * '설정 정보 사용 특징'
 * - @Bean(initMethod, destroyMethod) 지정
 * - 메서드 이름 자유, 스프링 빈이 코드에 의존하지 않음
 * - 외부 라이브러리에 적용할 수 있음
 * - @Bean 사용했을 때, destroyMethod default 지정되어 있다. (inferred;추론) -> close 나 shutdown 이름의 메서드 추론해서 호출해줘서 따로 안써도 됨
 *
 * '애노테이션 사용'
 * - 권장
 * - 외부 라이브러리에 적용하지 못한다 -> @Bean 쓰자
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
//        @Bean(initMethod = "init", destroyMethod = "close" /* 또는 destroyMethod="" */)
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
