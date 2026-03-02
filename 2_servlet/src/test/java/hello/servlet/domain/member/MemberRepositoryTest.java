package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
//    싱글톤을 사용하고 있기 때문에 객체를 새로 생성할 필요 없이 인스턴스를 가져오기만 하면됨
//    MemberRepository memberRepository = new MemberRepository();
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() { // 저장 기능 잘되는지 테스트
        //given 주어젔을 때
        Member member = new Member("hello", 20);

        //when 실행했을 때
        Member saveMember = memberRepository.save(member);

        //then 결과
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        Assertions.assertThat(result).contains(member1);
        Assertions.assertThat(result).contains(member2);
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}