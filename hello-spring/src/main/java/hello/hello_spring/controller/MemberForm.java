package hello.hello_spring.controller;

public class MemberForm {
    // 1. 필드 생성
    private String name;
    // 2. 필드에 맞는 게터 세터 생성
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
