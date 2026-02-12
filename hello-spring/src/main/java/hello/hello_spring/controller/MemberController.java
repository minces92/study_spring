package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm) {
        // 우리가 만들었떤  도메인 맴버에 객체 선언
        Member member = new Member();
        // 도메인 맴버데 데이터를 입력 받은 값에서 추가
        member.setName(memberForm.getName());
        // 멤버 서비스를 통하여 join 진행
        memberService.join(member);

        return "redirect:/"; // 홈화면으로 이동
    }

    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
