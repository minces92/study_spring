package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// I. frontcontroller 설정

// I-2. 서블릿 애노테이션 주소에 * 추가
@WebServlet(name = "FrontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// I-1. 서블릿화 확장
public class FrontControllerServletV1 extends HttpServlet {

    // I-3 필드생성
    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    // I-4 생성자와 패스와 컨트롤러 연결
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }
    // I-4 서비스 구현전에는 연결확인
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // I-4 response가 없으니 화면은 빈상태로 나오며 터미널에 찍힘
        System.out.println("FrontControllerServletV1.service");

        // II. 서비스 구현
        // II-1. URI 가져오기 (url 중 path만)
        String requestURI = req.getRequestURI();
        // II-2. Controller 중 요청 URI 찾기
        // 매핑되는 컨트롤러의 객체 인스턴스가 반환됨
        // 그걸 미리 만들어둔 ControllerV1으로 받으면 일관성있게 반환됨
        ControllerV1 controller = controllerMap.get(requestURI);
        // II-3 혹시라도 없으면
        if (controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // II-4. 있다면 해당 컨트롤러 프로세스 실행
        controller.process(req, resp);
    }
}
