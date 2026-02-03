package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(MemberService memberService, MemberRepository memberRepository){
        return new MemberService(memberRepository()); //파라미터 아래 메소드를 넣어주면 됩니다.
    }

    @Bean
    public MemberRepository memberRepository(){ // 인터페이스
        return new MemoryMemberRepository(); // 인터페이스의 구현체
    }
}
