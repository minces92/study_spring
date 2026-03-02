package hello.servlet.basic;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name= "helloServlet", urlPatterns = "/hello") // Ctrl + O 누르면 자동 오버라이딩
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); // /hello 접속시 화면상에는 아무것도 없지만 터미널에 출력
        System.out.println("req = " + req); // ServletRequest에서 상속되어 만들어진 객체
        System.out.println("res = " + res); // ServletResponse에서 상속되어 만들어진 객체
        // request  테스트
        // ~/hello?username = kim 접속시
        String username = req.getParameter("username"); // 쿼리파라미터의 키를 가져와 넣는다.
        System.out.println("username = " + username);
        // response 테스트
        // contenttype 과 enchodig은 필수
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println("<h1>Hello World!</h1>" + username);
    }
}
