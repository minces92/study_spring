package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {
    // 0. 컨트롤러가 갖는 req resp 하는 HTTPServlet process를 선언합니다.
    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


}
