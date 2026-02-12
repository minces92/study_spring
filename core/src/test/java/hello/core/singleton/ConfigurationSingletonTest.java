package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationSingletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository msMemberRepository = memberService.getMemberRepository();
        MemberRepository osMemberRepository = orderService.getMemberRepository();
        MemberRepository MemoryMemberRepository = ac.getBean("memberRepository",  MemberRepository.class);

        System.out.println("msMemberRepository =  " + msMemberRepository);
        System.out.println("osMemberRepository =  " + osMemberRepository);
        System.out.println("MemoryMemberRepository =  " + MemoryMemberRepository);

        Assertions.assertThat(msMemberRepository).isSameAs(osMemberRepository);

    }

    @Test
    void configurationBeanDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
    
    
}
