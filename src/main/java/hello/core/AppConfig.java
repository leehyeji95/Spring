package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 전체 동작 방식을 구성(config)하기 위해
// 구현 객체를 생성하고 '연결'하는 책임을 가지는 별도의 설정 클래스
// 생성한 객체 인스턴스의 참조(레퍼런스)를 '생성자를 통해서 주입(연결)' 해준다.

// @Configuration 없이 @Bean 만 등록한 경우 -> Bean 등록은 되지만, 싱글톤 보장이 되지 않는다.
// 따라서, Spring 설정정보에는 @Configuration 넣기
@Configuration  // 애플리케이션 구성/설정 정보
public class AppConfig {
    // 구성영역은 변경 당연, 사용 영역은 변경 없음

    @Bean   // Spring Container 에 등록되는 메서드
    public MemberService memberService() {
        // 생성자 주입을 통해 의존성 주입
        return new MemberServiceImpl(memberRepository());
    }

    // AppConfig 리팩토링 (역할과 구현의 분리)
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        // 추후 변경 시 이 부분만 수정 -> DbMemberRepository 등...
    }

    @Bean
    public OrderService orderService() {
        // 역할 클래스
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 구현 클래스
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
