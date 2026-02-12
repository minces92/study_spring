package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // ctrl 누르고 누르면 템플릿 해당하는 연결된 곳으로 이동됨
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //api  사용하기 위해서는 필요한 어노테이션
    public  String helloString(@RequestParam("name") String name){
        return "hello " + name; // 태그도 없이 노출됨
    }

    @GetMapping("hello-api")
    @ResponseBody //api  사용하기 위해서는 필요한 어노테이션
    public Hello helloAPI(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // Hello라는 오브젝트를 사용하려면 클래스 선언 필요
    static class Hello {
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name =name;
        }

    }


}
