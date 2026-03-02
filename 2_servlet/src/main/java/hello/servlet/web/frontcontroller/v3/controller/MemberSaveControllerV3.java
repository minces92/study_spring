package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 코드는 이전에 서블릿 클래스에서 가져오기
        // servlet/MemberSaveServlet.java 에서 가져온다
        String username = paramMap.get("username");
        // req.getPrameter String으로 가져오기 때문에 형변환 필요합니다.
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
