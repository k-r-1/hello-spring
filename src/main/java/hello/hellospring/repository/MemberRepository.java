package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 객체를 저장하는 저장소
public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 회원이 반환됨.
    Optional<Member> findById(Long id); // id로 회원 찾기.
    Optional<Member> findByName(String name); // name으로 회원 찾기.
    List<Member> findAll(); // 지금까지 저장돈 모든 회원 리스트 반환.
}
