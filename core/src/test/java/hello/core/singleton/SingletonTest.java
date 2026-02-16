package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureJavaContainer() {
        AppConfig appConfig = new AppConfig();
        // 확인할 부분 호출할 때 마다 객체를 생성하는 지 확인 필요
        //
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);

//        Assertions.assertThat(memberService1).isSameAs(memberService2);// appConfig가 스프링 컨테이너가 아니기 때문에 새로운 객체 생성됨
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletoneService singletonService1 = SingletoneService.getInstance();
        SingletoneService singletonService2 = SingletoneService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        ApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
