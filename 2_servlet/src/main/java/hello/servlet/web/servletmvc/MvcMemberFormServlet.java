package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name= "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //요청이 올 때 원하는 위치로 전송시기 위하여 패스 지정
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // req.getRequestDispatcher(viewPath); : 컨트롤러에서 뷰로 이동하기 위해 사용
        // 의미 : 파라미터 경로로 이동할거야
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        // 서블릿에서 jsp를 호출할 수 있도록 해주는 역할
        dispatcher.forward(req,resp);

    }
}
