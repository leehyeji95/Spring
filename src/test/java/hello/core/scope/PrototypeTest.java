package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);    // 클래스 직접 등록 -> 컴포넌트 자동 등록
        System.out.println("find prototypeBean1"); // 프로토타입 빈 조회하기 바로 직전에 생성된다.
        // 생성 -> init -> 조회
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2"); // 프로토타입 빈 조회하기 바로 직전에 생성된다.
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // destroy 자동으로 안되기 때문에 직접 호출
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            // Close 호출 안됨 -> 관리하지 않음 (만들고 버림)
            System.out.println("PrototypeBean.destroy");
        }
    }
}
