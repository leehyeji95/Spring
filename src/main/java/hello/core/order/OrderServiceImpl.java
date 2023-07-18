package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    // 회원 찾기
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // DIP 위반
    // 할인 정책
//    DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 변경하는 순간 OCP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // DIP 위반(추상, 구현체에도 의존)

    // final 키워드 -> 생성자를 사용해서 기본으로 할당 돼있어야 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // OrderService 입장에서 할인에 대한 정책은 discountPolicy에게 넘김 (단일체계원칙 잘 지킴)
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
