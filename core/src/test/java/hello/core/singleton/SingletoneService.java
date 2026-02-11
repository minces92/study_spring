package hello.core.singleton;

public class SingletoneService {
    // 1. private 활용하여 static 영역에 하나만 생성되도록 유도한다.
    private static final SingletoneService instance = new SingletoneService();
    // 2. public으로 열어서 객체 인스턴스가 필요하면
    // 아래 static 메서드를 통해서만 조회 하도록
    public static SingletoneService getInstance() {
        return instance;
    }

    // 3. 같은이름의 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletoneService() {}

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
