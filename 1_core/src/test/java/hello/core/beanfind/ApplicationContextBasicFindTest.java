package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("[memberService] = " + memberService);
        System.out.println("[memberService.getClass()] = " + memberService.getClass());
        // 클래스 조회
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); //이름없음

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") // 구현체 조회해도됨 기능에 의존하였기 때문에 좋은 건아니지만 해야할 때도 있는 법
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("[memberService] = " + memberService);
        System.out.println("[memberService.getClass()] = " + memberService.getClass());
        // 클래스 조회
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //실패 테스트
    @Test
    @DisplayName("빈 이름 조회(실패)")
    void findBeanByNameX(){
//        MemberService memberService = ac.getBean("xxxx", MemberService.class);
//        System.out.println("[memberService] = " + memberService);
//        System.out.println("[memberService.getClass()] = " + memberService.getClass());
        // 클래스 조회
//        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // 실패용 던지기
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx" , MemberService.class));
    }
}
