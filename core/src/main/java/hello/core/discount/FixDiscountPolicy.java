package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 정가 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            // Grade 의 값(eNum) == 으로 비교해도 되나요?
            // => Enum은 == 으로 비교가 맞음
            return discountFixAmount; // VIP 1000원 할인
        } else {
            return 0; // VIP 아님 할인 없어
        }
    }
}
