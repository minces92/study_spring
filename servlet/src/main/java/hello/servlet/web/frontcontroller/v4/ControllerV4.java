package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {
    // 0. 컨트롤러가 갖는 req resp 하는 HTTPServlet process를 선언합니다.
//    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    // v2. process에서 Myview 객체를 반환합니다.
//    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    // v3 process 에서 Mapping 만 하면됨
    // 이제 컨트롤러들은 서블릿에 더이상 종속이 필요없어짐
    // v4 process 파라미터에 모델 뷰가 없어지고 모델을 파라미터로 전송하고 스트링을 받음

    /**
     *
     * @param paramMap
     * @param model
     * @return  viewName
     */
    String process(Map<String , String> paramMap, Map<String, Object> model);
}
