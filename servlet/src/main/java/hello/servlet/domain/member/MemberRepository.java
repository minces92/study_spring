package hello.servlet.domain.member;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않습니다. HashMap 사용시
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
//@Repository
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    // 싱글 톤 직접 띄우기 ( 스프링 없이 사용)
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    };

    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long id) {
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        // new ArrayList 에 값을 넘기면서 store 자체 값을 건들고 싶지 않아서 이방식으로 전송함
        // store 값 자체를 보호하기 위한 것
    }

    public void clearStore(){
        // 이런 코드는 테스트에서 주로 사용함
        store.clear();
    }


}
