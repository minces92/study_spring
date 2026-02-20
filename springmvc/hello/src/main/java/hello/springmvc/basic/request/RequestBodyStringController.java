package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 바이트 코드로 가져오기
        ServletInputStream inputStream = request.getInputStream();
        // 문자열로 바디 가져오기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }
    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWiter) throws IOException {
        // 바이트 코드 가져오기 생략
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        // getWriter 가져오기 생략
        responseWiter.write("ok");
    }

    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        // HttpEntity<String> : Http message 형식의 스펙화된것
        // 바디 받기 뿐만아니라 보내기도 가능해서 메소드 리턴값 바꿔서 전송가능
        String body = httpEntity.getBody();

        log.info("messageBody={}", body);
        // getWriter 가져오기 생략 ok 찍기
        return new HttpEntity<>("ok");
    }

    /**
     * // request Entity 와 respons Entity는 httpEntity를 상속하기 때문에
     * // 거의 기능이 유사함
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @RequestMapping("/request-body-string-v3-1")
    public HttpEntity<String> requestBodyStringV3_1(RequestEntity<String> httpEntity) throws IOException {

        String body = httpEntity.getBody();

        log.info("messageBody={}", body);
        // 응답 바디와 함께 상태 숫자를 보낼 수 있음
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
        // 201 create 상태
    }

    @ResponseBody
    @RequestMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        // @RequestBody 에의하여 HttpEntity관련 사용할 필요 없어짐

        log.info("messageBody={}", messageBody);
        // @ResponseBody 기능으로 텍스트로 반환
        return "ok";
    }
}
