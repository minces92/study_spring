package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

class ApplicationContextSameBeanFindTest {

//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration // 클래스 내의 정적 클래스의 의미 현재 클래스에서만 현재 클래스를 사용하겠다.
    static class SameBeanConfig    {
        // 따라서 같은 타입 빈을 조회하기 위한 임시 용 config 라고 보시면됩니다.
        @Bean
        public MemberRepository memberRepository1(){ return new MemoryMemberRepository();  }
        @Bean
        public MemberRepository memberRepository2(){ return new MemoryMemberRepository();  }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류 발생!")
    void findBeanByTypeDuplicate(){
        // GetBean은 싱글만 찾아서 NoUniqueBeanDefinitionException 오류 발생
//        MemberRepository bean = ac.getBean(MemberRepository.class);
        //NoUniqueBeanDefinitionException 오류
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(MemberService.class));
    }

    @Test
    @DisplayName("빈 이름으로 클래스(인스턴스) 일치 확인")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1" ,MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key :  beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beanOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

}
