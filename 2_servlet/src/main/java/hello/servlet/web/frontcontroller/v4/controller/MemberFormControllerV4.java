package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
//        MyView ModelView = new ModelView("new-form.jsp");
        // 필요한 상대 주소만 보내주면됨
//        ModelView ModelView = new ModelView("new-form");
//        return new ModelView("new-form");
        // V4 파일명만 전달
        return "new-form";
    }
}
