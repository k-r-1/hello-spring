package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 스프링 설정 파일이라는 의미 (스프링이 읽어서 설정 적용함)
public class SpringConfig {

    @Bean // 이 메서드가 리턴하는 객체를 스프링 빈으로 등록하라는 뜻
    public MemberService memberService() {
        // 직접 memberRepository()를 호출해서 넣어줌 → 수동 DI
        return new MemberService(memberRepository());
    }

    @Bean // 이 메서드가 리턴하는 객체도 빈으로 등록됨
    public MemberRepository memberRepository() {
        // 구현체인 MemoryMemberRepository를 리턴 → 인터페이스 → 구현체 주입
        return new MemoryMemberRepository();
    }
}
