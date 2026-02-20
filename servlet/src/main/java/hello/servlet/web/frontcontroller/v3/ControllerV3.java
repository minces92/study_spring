package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    // 0. 컨트롤러가 갖는 req resp 하는 HTTPServlet process를 선언합니다.
//    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    // v2. process에서 Myview 객체를 반환합니다.
//    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    // v3 process 에서 Mapping 만 하면됨
    // 이제 컨트롤러들은 서블릿에 더이상 종속이 필요없어짐
    ModelView process(Map<String , String> paramMap);
}
