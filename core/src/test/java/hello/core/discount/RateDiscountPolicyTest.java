package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인 대상")
    void vip_check(){
        //given
        Member member = new Member(1L, "nameVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        System.out.println("[discount] : " + discount);
        assertThat(discount).isEqualTo(1000);
    }
    // 성공 테스트를 해봤따면
    // 실패하는 테스트도 만들어봐야해

    @Test
    @DisplayName("VIP 가 아니라면 할임 없음")
    void vip_none(){
        //given
        Member member = new Member(1L, "nameVIP", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        System.out.println("[discount] : " + discount);
        assertThat(discount).isEqualTo(0);
    }
}
