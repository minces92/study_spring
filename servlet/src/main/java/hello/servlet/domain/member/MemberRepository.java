package hello.servlet.domain.member;


import java.util.HashMap;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않습니다. HashMap 사용시
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L
    // 싱글 톤 직접 띄우기 ( 스프링 없이 사용)
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {return instance};

    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long id) {
        return store.get(id);
    }


}
