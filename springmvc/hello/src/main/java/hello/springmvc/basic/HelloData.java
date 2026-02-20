package hello.springmvc.basic;

import lombok.Data;

@Data // 자동 생성자 게터 세터 투스트링
public class HelloData {

    private String username;
    private int age;
}
