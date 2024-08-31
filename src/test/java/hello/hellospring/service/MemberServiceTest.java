package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; // MemberService 인스턴스를 저장할 변수 선언
    MemoryMemberRepository memberRepository; // MemoryMemberRepository 인스턴스를 저장할 변수 선언

    @BeforeEach
    public void beforeEach() {
        // 각 테스트 메서드 실행 전에 수행됨. 여기서 테스트에 사용할 리포지토리와 서비스 인스턴스를 초기화.
        memberRepository = new MemoryMemberRepository(); // 새로운 MemoryMemberRepository 객체 생성
        memberService = new MemberService(memberRepository);  // 생성된 리포지토리를 주입하여 MemberService 객체 생성
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given: 테스트 준비 단계, 테스트에 필요한 데이터나 상황을 설정
        Member member = new Member();
        member.setName("hello"); // 새로운 회원 객체 생성 및 이름 설정

        // when: 실제 테스트를 실행하는 단계, 주로 테스트할 메서드를 호출
        Long saveId = memberService.join(member); // 회원가입 메서드를 호출하고 결과로 회원 ID를 반환받음

        // then: 테스트 결과를 검증하는 단계, 예상 결과와 실제 결과를 비교
        Member findMember = memberService.findOne(saveId).get(); // 저장된 회원을 조회
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // 저장된 회원의 이름과 조회한 회원의 이름이 같은지 검증
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring"); // 첫 번째 회원 생성
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 중복된 이름을 가진 두 번째 회원 생성

        //when
        memberService.join(member1); // 첫 번째 회원가입 시도

        // assertThrows로 발생한 예외를 잡아내고, 그 예외의 메시지를 검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        // try-catch 구문으로 예외가 발생하는지 확인
        try {
            memberService.join(member2); // 중복된 회원 가입 시도
            fail(); // 예외가 발생하지 않으면 테스트 실패
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외 메시지가 예상과 같은지 검증
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}