package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberTest1", Grade.BASIC);

        // when
        memberService.join(member);
        Member memberT = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member).isEqualTo(memberT);
    }


}
