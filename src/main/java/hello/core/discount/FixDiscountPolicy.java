package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정액할인정책
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;   // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        // 회원 등급이 VIP 인 경우, 1000원 할인
        if(member.getGrade() == Grade.VIP) {    // Enum Type은 == 비교연산
            return discountFixAmount;
        } else {
            // 할인 없음
            return 0;
        }
    }
}
