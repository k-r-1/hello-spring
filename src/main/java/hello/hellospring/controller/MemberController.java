package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 의존성 주입(DI)을 의미함. 스프링이 자동으로 memberService 객체를 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;  // 주입받은 memberService를 내부 필드에 할당
    }
}
