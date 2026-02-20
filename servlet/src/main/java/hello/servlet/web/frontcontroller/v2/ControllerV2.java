package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    // 0. 컨트롤러가 갖는 req resp 하는 HTTPServlet process를 선언합니다.
//    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    // v2. process에서 Myview 객체를 반환합니다.
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
