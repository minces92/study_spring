package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

//    @RequestMapping(value = "/save", method =  RequestMethod.POST)
    @PostMapping("/save")
    public String Save(
        // @RequestPram이라는 애노테이션을 통해 따로 호출할 필요 없이 request의 내용을 바로 받을 수 있음
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            // 애노테이션 기반은 유연해서 모델 추가해도 문제 안생김
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

//    @RequestMapping(method=RequestMethod.GET)// 없는 경우는 지우면된다 /springmvc/v2/members
    @GetMapping
    public String members(Model model) {
        // 여기선 요청 파라미터가 없으므로 데이터를 담을 모델만 필요
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members"; // 여기는 패스가 아닌 viewnamed이다
    }

}
