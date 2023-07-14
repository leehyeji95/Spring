package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderSerivceImpl implements OrderService {
    // 회원 찾기
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // DIP 위반
    // 할인 정책
//    DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 변경하는 순간 OCP 위반
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // DIP 위반(추상, 구현체에도 의존)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // OrderService 입장에서 할인에 대한 정책은 discountPolicy에게 넘김 (단일체계원칙 잘 지킴)
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
