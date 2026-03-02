package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// I. frontcontroller 설정

// I-2. 서블릿 애노테이션 주소에 * 추가
@WebServlet(name = "FrontControllerServletV4", urlPatterns = "/front-controller/v4/*")
// I-1. 서블릿화 확장
public class FrontControllerServletV4 extends HttpServlet {

    // I-3 필드생성
    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    // I-4 생성자와 패스와 컨트롤러 연결
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    // I-4 서비스 구현전에는 연결확인
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // I-4 response가 없으니 화면은 빈상태로 나오며 터미널에 찍힘
        System.out.println("[FrontControllerServletV4.service]");

        // II. 서비스 구현
        // II-1. URI 가져오기 (url 중 path만)
        String requestURI = req.getRequestURI();
        // II-2. Controller 중 요청 URI 찾기
        // 매핑되는 컨트롤러의 객체 인스턴스가 반환됨
        // 그걸 미리 만들어둔 ControllerV1으로 받으면 일관성있게 반환됨
        ControllerV4 controller = controllerMap.get(requestURI);
        // II-3 혹시라도 없으면
        if (controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // II-4. 있다면 해당 컨트롤러 프로세스 실행
//        controller.process(req, resp);

        // v2 II-4. process MyView 객체 반환
//        MyView view = controller.process(req, resp);
        // v3 II-4 process ModelView 객체반환
        // v3 II-4-1 파리미터에는 paraMap이 들어가 줘야함
        // v3 II-4-2 전체 파라미터 뽑는 식인데 Ctrl+Alt+M 눌러서 메소드화하자
        Map<String, String> paramMap = createParamMap(req);
//        // v3 II-4-2 req 역할 하던건 mv로 꺼내긴했는데
//        ModelView mv = controller.process(paramMap);
//        // 지금 상태에서는 이름 view 이름 밖에 못꺼내서
//        String viewName = mv.getViewName();
//        // view.render까지 해줄 viewResolver가 만들어져야한다.
//        // 어차피 MyView를 통해 보내야하기 때문에 주소만 줄인걸 쓰면된다
//        // 계층을 맞추기 위해 Ctrl Alt M 해서 메소드화 해준다
//        MyView view = viewResolver(viewName);
        //v4 모델도 함께 넘어가야하므로 모델도 가져와야함
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        // v2 II-5. 반복되던 dispatcher forward를 한줄로 끝
//        view.render(req, resp);
        // v3 모델 파라미터 추가
//        view.render(mv.getModel(), req, resp);
        // v4 모델을 미리 선언해둔걸로 가져온다
        view.render(model, req, resp);

    }

    private @NonNull MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paraName -> paramMap.put(paraName, req.getParameter(paraName)));
        return paramMap;

    }
}
