package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient  {

    private  String url;
    public   NetworkClient(){
        System.out.println("[생성자] url = " + url);
        connect();
        call("[생성자] 초기 연결 메세지");
    }
    public void setUrl(String url) {
        this.url = url;
    }
    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("NetworkClient.connect : " + url);
    }
    public void  call(String message){
        System.out.println("NetworkClient.call : " + url + "/ message : " + message);
    }
    // 서비스 종료 시 호출
    public void disconnect(){
        System.out.println("NetworkClient.disconnect : "   +   url);

    }

    /**
     * 빈 콜백 1. InitalizingBean 및 DisposableBean 을 적용해보자
     * 인터페이스 이므로 implement
     * InitalizingBean 인터페이스 내 메소드 : afterPropertiesSet
     * DisposableBean 인터네피으 내 메소드 : destory
     * 오버라이딩 필요
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        // afterPropertiesSet : InitalizingBean에서 의존관계 주입이 끝나면 실행을 도와주겠다.
//        System.out.println("[InitalizingBean afterPropertiesSet] url = " + url);
//        connect();
//        call("[InitalizingBean afterPropertiesSet] 초기 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("[DisposableBean destroy] url = " + url);
//        disconnect();
//        call("[DisposableBean destroy] 종료 연결 메세지");
//    }

    /**
     * 빈콜백 2. 빈등록 초기화, 소멸 메서드
     * 원하는 이름 메서드로 하여 시작과 끝을 만들어 놓습니다.
     * 빈등록하는 클래스 @Bean 에 파라미터로 아래 메소드들을 등록합니다.
     * 파라미터 키 : 시작(initMethod) / 소멸(destrorymethod)
     */

//
//    public void init()  {
//        System.out.println("[빈등록 초기화] url = " + url);
//        connect();
//        call("[빈등록 초기화] 초기 연결 메세지");
//    }
//
//    public void close()  {
//        System.out.println("[빈 소멸] url = " + url);
//        disconnect();
//        call("[빈 소멸] 종료 연결 메세지");
//    }
    /**
     * 빈콜백 3. 어노테이션 @PostConstructor @PreDestory
     * 앞서 사용했던 메소드 형태에서 메소드 위에 어노테이션 선언만 하면 끝
     * 초기화 : @PostConsturctor / 종료 : @PreDestory
     */

    @PostConstruct
    public void init()  {
        System.out.println("[빈등록 초기화] url = " + url);
        connect();
        call("[빈등록 초기화] 초기 연결 메세지");
    }

    @PreDestroy
    public void close()  {
        System.out.println("[빈 소멸] url = " + url);
        disconnect();
        call("[빈 소멸] 종료 연결 메세지");
    }
}
