package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name="RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");
        System.out.println("[전체 파라미터 조회] - start");
        // 0. getParameterNames() 모든 파라미터 다꺼내짐(객체 상태)
        System.out.println("[0. getParameterNames]");
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("parameterNames = " + parameterNames);
        // 1. 요즘방식(람다식)으로 선언 없이 하나씩 꺼내기
        System.out.println("[1. getParameterNames 람다식]");
        req.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + req.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();
        //http://localhost:8080/request-param?username=hello&age=20&username=hello2
        // 와 같이 같은 키에 값이 두개일 경우 위와 같은 단일 파라미터 조회하면
        // 먼저 잡히는 아무거나 가져오게 됩니다. 따라서 아래와 같은 방식을 활용합니다.
        System.out.println("[이름(키)가 같은 복수 파라미터 조회]");
        String[] usernams = req.getParameterValues("username");
        for(String name : usernams){
            System.out.println("username = " + name);
        }
        System.out.println();
        // response
        resp.getWriter().write("ok");

    }
}
