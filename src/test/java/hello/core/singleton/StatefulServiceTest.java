package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // 지역변수로 변경하기
//        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : B 사용자가 20000원 주문
        statefulService1.order("userB", 20000);

        // ThreadA: A 사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = "+price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000); // true
        assertThat(price).isNotEqualTo(10000); // false

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
