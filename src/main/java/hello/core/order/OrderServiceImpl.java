package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // 회원 찾기
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // DIP 위반
    // 할인 정책
//    DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 변경하는 순간 OCP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // DIP 위반(추상, 구현체에도 의존)

    // final 키워드 -> 생성자를 사용해서 기본으로 할당 돼있어야 한다. (생성자 주입 시)
    // 수정자 주입(setter) 시, final 키워드 없어야함
    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;

/*
    // 수정자 주입 -> 자바코드
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        // setter 쓰기 위해서 멤버 변수 private final -> final 키워드 없어야함
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }


    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " +discountPolicy);
        this.discountPolicy = discountPolicy;
    }

 */


    @Autowired  // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 자동주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
