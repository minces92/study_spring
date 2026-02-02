package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void join() {
        // given멤버의 생성자 정보 그리고 이름 지정 - setName
        Member member = new Member();
        member.setName("joinTestName");

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
        member2.setName("member2");

        // when
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}