package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "member1", Grade.VIP);

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberService.join(member);
        Member findMember = memberRepository.findById(member.getId());
        Order order = orderService.createOrder(findMember.getId(), "item1", 50000);

        System.out.println("order = " + order);
        System.out.println("order.calculate = " + order.calculatePrice());
    }
}
