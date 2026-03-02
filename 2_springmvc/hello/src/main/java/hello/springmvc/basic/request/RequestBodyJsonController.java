package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  [서블릿에 했떤것과 같은 내용]
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    /**
     * [@RequsetMaaper @ResponseMapper 도입]
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        // objectMapper 변환이 귀찮음
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return  "ok";
    }
    // HelloData 꼭 가져와야했던거 간소화 방법 : ModelAttribute() 있었잖아 그거 활용
    // @RequestBody 생략시 @ModelAttribue로 인식되어 에러발생
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        // @ModelAttrebute는 여기서 사용하는 애노테이션이 아니라서 그냥 넣으면 끝
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return  "ok";
    }
    // 혹시 httpEntity 쓰고 싶으면 써도됨
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData helloData = httpEntity.getBody();
        // @ModelAttrebute는 여기서 사용하는 애노테이션이 아니라서 그냥 넣으면 끝
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return  "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {
        // @ModelAttrebute는 여기서 사용하는 애노테이션이 아니라서 그냥 넣으면 끝
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return  helloData;
    }

}
