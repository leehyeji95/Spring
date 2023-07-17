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

// 애플리케이션의 전체 동작 방식을 구성(config)하기 위해
// 구현 객체를 생성하고 '연결'하는 책임을 가지는 별도의 설정 클래스
// 생성한 객체 인스턴스의 참조(레퍼런스)를 '생성자를 통해서 주입(연결)' 해준다.
public class AppConfig {
    // 구성영역은 변경 당연, 사용 영역은 변경 없음
    public MemberService memberService() {
        // 생성자 주입을 통해 의존성 주입
        return new MemberServiceImpl(memberRepository());
    }

    // AppConfig 리팩토링 (역할과 구현의 분리)
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        // 추후 변경 시 이 부분만 수정 -> DbMemberRepository 등...
    }

    public OrderService orderService() {
        // 역할 클래스
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        // 구현 클래스
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
