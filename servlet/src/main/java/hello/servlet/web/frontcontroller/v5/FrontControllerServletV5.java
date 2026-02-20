package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import java.util.*;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // I-3 필드생성
    // 기존 필드 하나의 컨트롤러로 고정
//    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    // V5. 고정시키지 않기 위해 controller  위치에 Object 투입
    // 더이상 컨트롤러만 관여하지 않기 때문에 handler라고 명명
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 여러개의 핸들러 중에서 꺼내와야함 (우리 컨트롤러 처음에 컨트롤로 매핑하는거랑 같음)
    private final List<MyHandlerAdapter>  handlerAdapters = new ArrayList<>();

    // I-4 생성자와 매핑정보 기입
    // 기존과 같이 일단 입력


    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v4 매핑 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    private boolean initHandlerAdapters() {
        return handlerAdapters.add(new ControllerV3HandlerAdapter())
        || handlerAdapters.add(new ControllerV4HandlerAdapter());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 만들고 또 메서드로 묶자
        Object handler = getHandler(req);

        if (handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 적절한 컨트롤러와 연결되게 하는 로직
//        handlerAdapters.iter +엔터하면 for문 자동완성
//      만들어진 식을 메서드 화
        MyHandlerAdapter ma = getHandlerAdapter(handler);
        ModelView mv = ma.handle(req, resp, handler);

        // 지금 상태에서는 이름 view 이름 밖에 못꺼내서
        String viewName = mv.getViewName();
        // view.render까지 해줄 viewResolver가 만들어져야한다.
        // 어차피 MyView를 통해 보내야하기 때문에 주소만 줄인걸 쓰면된다
        // 계층을 맞추기 위해 Ctrl Alt M 해서 메소드화 해준다
        MyView view = viewResolver(viewName);

        // v2 II-5. 반복되던 dispatcher forward를 한줄로 끝
        view.render(mv.getModel(), req, resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter ma;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)){
                return adapter;
            }
        }
        // 적절한 핸들러가 없다면
        throw new IllegalArgumentException("[IAE] handler adapter not found");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
    private @NonNull MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
