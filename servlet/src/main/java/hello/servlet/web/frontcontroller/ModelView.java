package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    // 필드 생성
    // view의 논리적 이름들 과 모델에 대한 것을 가져옵니다.
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    //생성자 생성
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    // setter getter 필요 롬복 써도 되고 만들어도되고

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
