package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 의존성 주입 방법
    // 1. 필드 주입
    // @Autowired private MemberService memberService;
    // 단점 : 주입 될 때 이후 바꿀 수가 없기 때문에 비추천한다.

    // 2. setter 주입
    /*
    private final MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    // 저 setMemberService라고 하는 메소드가 계속 열려있기 때문에 변경할 것도 없는데 열려있는 것
    // 단점 : 언제든 열려있어서 필요 없을 때에도 호출되게 된다.
     */

    // 3. 생성자 주입
    // 장점 어플리케이션이 조립되는 과정에서 바로 셋팅하고 끝
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
