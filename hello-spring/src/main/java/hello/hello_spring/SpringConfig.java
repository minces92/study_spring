package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


    // JdbcMemberRespository 용 spring에서 지원하는 DataSource
    // 이것 또한 AutoWired 이용하거나 생성자 생성하면됩니다.
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository){
        return new MemberService(memberRepository()); //파라미터 아래 메소드를 넣어주면 됩니다.
    }

    @Bean
    public MemberRepository memberRepository(){ // 인터페이스
        // 임시용 0. 메모리사용
        //return new MemoryMemberRepository(); // 인터페이스의 구현체
        // 1. 순수 jdbc
        return new JdbcMemberRepository(dataSource); // 여기서는 필드 DataSource가 필요한데 이건 Spring에서 지원해줍니다. 필드에추가
    }
}
