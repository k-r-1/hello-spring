package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // store라는 이름의 HashMap을 사용하여 회원 데이터를 저장.
    private static long sequence = 0; // sequence 변수를 사용하여 회원의 고유 ID를 관리.

    @Override
    public Member save(Member member) { // 새로운 회원이 가입할 때,
        member.setId(++sequence); // sequence 값이 하나씩 증가하여 고유한 ID가 부여됨.
        store.put(member.getId(), member); // 회원 정보를 store라는 저장소에 저장(ID와 회원 정보를 함께 보관).
        return member; // 회원 객체 반환.
    }

    @Override
    public Optional<Member> findById(Long id) { // 주어진 id로 회원을 찾아서,
        return Optional.ofNullable(store.get(id)); // store.get(id)가 null일 경우, Optional.empty()를 반환하고, 값이 있으면 그 값을 Optional로 감싸서 반환함.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 저장된 모든 회원 중에서,
                .filter(member -> member.getName().equals(name)) // 입력된 name과 같은 이름을 가진 회원만 남김.
                .findAny(); // 조건에 맞는 회원 중 하나를 찾아 반환하고, 만약 없으면 ptional.empty()를 반환함.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장된 모든 회원들을 리스트 형태로 반환.
    }

    public void clearStore() {
        store.clear(); // 저장된 모든 데이터 삭제.
    }
}
