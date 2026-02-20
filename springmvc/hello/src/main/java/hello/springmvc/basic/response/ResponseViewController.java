package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "Hello Spring MVC");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "Hello Spring MVC");
        return "response/hello";
    }

    /**
     * 혹시라도 @ResponseBody 하면 연결안되요
     * Rest 형태가 되어버림
     */
    // void 반환 경우 : @controller, HttpServletResponse, OutStream(Wrter) 같은게 없다면 사용 가능
    // 명시성 너무 떨어짐
    // 추가로 뷰의 놀리명을 패스로 넣으면 반환 없이도 되는데
    // 추천하지는 않습니다.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "Hello Spring MVC");
    }
}
