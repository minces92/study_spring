package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 코드는 이전에 서블릿 클래스에서 가져오기
        // servlet/MemberSaveServlet.java 에서 가져온다
        String username = req.getParameter("username");
        // req.getPrameter String으로 가져오기 때문에 형변환 필요합니다.
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다
        req.setAttribute("member",member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        req.getRequestDispatcher(viewPath).forward(req,resp);

    }
}
