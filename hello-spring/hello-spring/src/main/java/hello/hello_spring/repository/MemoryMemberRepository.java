package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 업무용의경우 공유되는 변수의 경우 컨커런해쉬맵을 사용해야하지만 일단 예제니까 스킵
    private static long sequence = 0L;
    // 업무용의 경우 어텀 롱을 사용해야하지만 지금은 예제니까 가볍게 진행합시다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member를 추가하려고 하면 일단 sequence 값을 먼저 1 올려줍니다.(시스템이 정해줘야하므로)
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member-> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
