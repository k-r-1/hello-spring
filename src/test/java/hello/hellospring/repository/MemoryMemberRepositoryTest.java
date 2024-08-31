package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 테스트가 끝날 때마다 저장소 초기화
    }

    @Test
    public void save() {
        Member member = new Member(); // 새로운 Member 객체 생성
        member.setName("spring"); // Member 객체의 이름을 "spring"으로 설정

        repository.save(member); // 객체를 리포지토리에 저장

        // 저장된 객체를 ID로 검색하여 가져옴.
        Member result = repository.findById(member.getId()).get(); // 반환 타입이 옵셔널. 옵셔널에서 값을 꺼낼 때는 get으로 꺼낼 수 있음..
        // member와 result가 같은지를 비교.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member(); // Member 객체를 생성
        member1.setName("spring1"); // 첫 번째 회원의 이름을 "spring1"으로 설정
        repository.save(member1); // member1을 리포지토리에 저장

        Member member2 = new Member(); // 두 번째 Member 객체를 생성
        member2.setName("spring2"); // 두 번째 회원의 이름을 "spring2"으로 설정
        repository.save(member2); // member2를 리포지토리에 저장

        // "spring1"이라는 이름으로 회원을 검색
        Member result = repository.findByName("spring1").get();
        // result가 member1과 동일한지 확인
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 저장된 모든 회원을 리스트로 가져옴.

        assertThat(result.size()).isEqualTo(2); // 리스트에 저장된 회원의 수가 2인지 확인.
    }
}
