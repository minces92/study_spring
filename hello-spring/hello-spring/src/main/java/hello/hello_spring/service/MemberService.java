package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // 먼저, 서비스를 위해서 회원용 리파짓토리가 필요하므로 가져옵시다.
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원서비스 기능 1 : 회원 가입
    public Long join(Member member) {

        /* 1단계 직접 옵셔널 선언해서 가져오기
        // 조건 추가 : 같은 이름 있으면 가입불가
        // 좋은 단축키 안내 : Ctrl + Alt + V : 자동으로 해당 함수의 옵션에 해당하는 변수형 지정해줌
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 여기서 그냥 get 과 if문으로 으로도 확인할 수 있겠지만
        // result.get() 이나 result.orElseGet() 등을 활용할 수 있지만 직접 꺼내는건 권장하지 않는다.
        // 변수.ifPresent 변수 값이 있으면 opthinal 에 포함된 함수입니다.
        // IllegalStateException
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 2단계 위 Optional 부터 코드도 줄일 수 있는데 변수 선언을 할 필요가 없다.
        /*
         아래와 같이 선언 없이 한번에 묶을 수 있다는 거 참고!
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });;
        */

        // 3단계 권장 : 한번에 변수 선언 없이 가져오고 그걸 메소드로 만들어서 가져오기
        // 2단계 상태에서 리팩토링 해서 Extract 메소드로 진행한다. (오른쪽 마우스 > 리펙토링 > 메소드 추출)
        validateDuplicateMember(member);

        memberRepository.save(member); // 저장을 호출하면 회원가입이지 뭐
        return member.getId(); // id 값을 반환해주기로 합시다.
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        ;
    }

    // 서비스 기능 2 : 회원 찾기
    public List<Member> findMembers(){
        return memberRepository.findAll(); // 반환되는 형식만 확인해주고 진행 끝
    }

    public Optional<Member> findOne (Long id){
        return memberRepository.findById(id);
    }
}
