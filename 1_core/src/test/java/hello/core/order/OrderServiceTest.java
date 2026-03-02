package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberRepository memberRepository = new MemoryMemberRepository();
//    OrderService orderService = new OrderServiceImpl();
//    MemberService memberService = new MemberServiceImpl();
    AppConfig appConfig = new AppConfig();
//    MemberRepository memberRepository = 1;
    OrderService orderService = appConfig.orderService();
    MemberService memberService = appConfig.memberService();

    @Test
    public void createOrder() {
        Member member = new Member(1L, "test1", Grade.VIP);
        memberService.join(member);

//        Member findMember = memberRepository.findById(member.getId());
        Order order = orderService.createOrder(member.getId(), "itemTestName", 10000);

        System.out.println("[order] : " + order.toString());
        System.out.println("[order.calculate] " + order.calculatePrice());

        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
