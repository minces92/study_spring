package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    // 다른 테스트들이 이상이 없기 위해서는 clear 필요
//    // clear 하려면 리파짓 토리 필요
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach () {
        // 1. 같은 리포지토리를 먼저 만들고
        memberRepository = new MemoryMemberRepository();
        // 2. 서비스에 그 리포지토리를 주입해줍니다.
        memberService = new MemberService(memberRepository);
    }

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
        org.assertj.core.api.Assertions.assertThat(e.getMessage())
                .isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}