package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 회원 서비스 관련 비즈니스 로직을 처리하는 클래스
public class MemberService {

    private final MemberRepository memberRepository; // MemberRepository 인터페이스를 사용해 리포지토리를 참조

    public MemberService(MemberRepository memberRepository) {
        // MemberService 인스턴스를 생성할 때, 외부에서 주입된 리포지토리를 사용하도록 설정
        this.memberRepository = memberRepository; // 주입된 리포지토리를 클래스의 필드에 할당
    }

    // 회원가입 메서드
    public long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        // 회원 정보를 저장소에 저장
        memberRepository.save(member);
        // 저장된 회원의 ID 반환
        return member.getId();
    }

    // 중복 회원 검증 메서드: 이미 같은 이름의 회원이 있는지 확인
    private void validateDuplicateMember(Member member) {
        // 이름으로 회원을 검색하여 중복된 회원이 있는지 확인
        memberRepository.findByName(member.getName())
                // 이미 존재하는 경우 예외 발생
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 메서드
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 모든 회원 정보를 리스트로 반환
    }

    // 특정 회원 조회 메서드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // ID로 회원을 찾아서 반환
    }
}
