package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given멤버의 생성자 정보 그리고 이름 지정 - setName
        Member member = new Member();
        member.setName("joinTestName1");

        // when : 회원 등록 함수 - memberService.jsoin
        Long savedId = memberService.join(member);

        //then : 해당 회원 조회 함수
        Member findmember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");

        // when
        memberService.join(member1);
        //then
        // 1. try catch
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            System.out.println(e.getMessage());
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // 2. try catch 보다는 함수로 검증하는 것이 좋다.
        // then
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalStateException.class,
                () -> memberService.join(member2) // 이 로직을 실행했을 때 예외가 발생해야 함
        );

        // 예외 메시지 검증 (AssertJ 사용)
        Assertions.assertThat(e.getMessage())
                .isEqualTo("이미 존재하는 회원입니다.");
    }

}