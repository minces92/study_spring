package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

import java.util.Arrays;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Member member = new Member(1L, "member1", Grade.VIP);

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberService.join(member);
        Member findMember = memberRepository.findById(member.getId());
        Order order = orderService.createOrder(findMember.getId(),"item1",50000);

        System.out.println("order = " + order);
        System.out.println("order.calculate = " + order.calculatePrice());
    }
}
