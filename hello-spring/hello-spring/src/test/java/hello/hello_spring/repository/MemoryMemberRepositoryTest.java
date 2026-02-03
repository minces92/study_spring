package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.xml.transform.Result;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// test 용이기 때문에 public이 꼭 필요하지는 않습니다. 제거
class MemoryMemberRepositoryTest {

    // 원본 코드인 main을 호출해서 사용합니다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 위변수가 매번 테스트 실행 후 클리어가 되어있어야 오류가 발생하지 않습니다.
    // 따라서, @AfterEach 매번 실행 후 실행한다는 콜백 어노테이션을 작성해줍니다.
    @AfterEach
    public void afterEach () {
        // 깔끔하게 하기 위해서 MemoryMemberRepository에 clearStore 함수를 만들어주고 여기서 사용해준다.
        repository.clearStore();
    }

    // 그 중에 첫벗 째 메소드 save() 를 테스트 해봅시다.
    // 어노테이션 @Test 를 입력하면 실행 할 수 있게 됩니다.
    // 단! 충돌 방지를 위하여 기존 서버는 종료한고 실행해 봅시다.
    // 아무것도 없는 상태에서 실행되는 것을 확인 할 수 있습니다.
    @Test
    public void  save(){
        // 여기서 부터 쓰는 코드는 save라는 함수를 통해서 기존의 save 함수를 검증한다고 생각하면된다.
        Member member = new Member();
        member.setName("name Is Spring");
        // 저장 호출
        repository.save(member);
        // 저장 잘되어있는지 확인 (findByID 활용)
        // findById 의 return 형태가 Optional이기 때문에 get 함수를 사용해서 가져올 수 있다.
        Member result = repository.findById(member.getId()).get();
        // 둘이 완전이 같으면 성공이겠지?
        // 확인방법 1 : system 로그 직기 (이걸 매번 확인할 수 없음)
        System.out.println("result = " + (result==member));
        // 확인방법 2 : Assertions.assertEquals를 활용하기
        Assertions.assertEquals(member, result);
        // 결과는 터미널에서 초록불 또는 에러시 에러 표시로 뜹니다.
        // 확인방법 3 : Assert.assertThat()
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void  findByName(){
        // 확실한 검증을 위해 여러개를 셋팅하고 테스트를 진행해봅시다.
        Member member1 = new Member();
        member1.setName("name Is Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("name Is Spring2");
        repository.save(member2);

        // 검증
        Member result = repository.findByName("name Is Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void  findAll(){
        // 확실한 검증을 위해 여러개를 셋팅하고 테스트를 진행해봅시다.
        Member member1 = new Member();
        member1.setName("name Is Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("name Is Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
