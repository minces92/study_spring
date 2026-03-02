package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


public class ScopeSingletonWithPrototype {


    @Scope("singleton")
    static  class SingletonTestBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("SingletonScopeTest.init");
        }
        @PreDestroy
        public void  destroy()
        {
            System.out.println("SingletonScopeTest.destroy");
        }
    }

    @Scope("prototype")
    static  class PrototypeTestBean
    {
        private int count = 0;
        public void addCount() {
            count++;
        }
        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeScopeTest.init");
        }
        @PreDestroy
        public void  destroy()
        {
            System.out.println("PrototypeScopeTest.destroy");
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    void  prototypeFind(){
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(PrototypeTestBean.class);

        PrototypeTestBean prototypeBean1 = acac.getBean(PrototypeTestBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.count).isEqualTo(1);

        PrototypeTestBean prototypeBean2 = acac.getBean(PrototypeTestBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.count).isEqualTo(1);
    }

    /**
     * 싱글톤과 프로토타입을 동시에 사용시 발생하는 문제
     * 싱글톤 내에서 프로토타입이 작동하는 경우 프로토타입 빈이라도 새로 만들어지지 않습니다.
     */
//    @Scope("singleton")
//    static  class ClientBean{
//        private final PrototypeTestBean prototypeBean;
//
//        @Autowired
//        public ClientBean(PrototypeTestBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public  int logic(){
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//
//    }
//
//    @Test
//    void  singletonClientUsePrototype() {
//        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeTestBean.class);
//        // 1. 클라이언트 빈 가져오기
//        ClientBean clientBean1 = acac.getBean(ClientBean.class);
//        int count1 = clientBean1.logic();
//        Assertions.assertThat(count1).isEqualTo(1);
//
//        // 2. 두번재 호출하기
//        ClientBean clientBean2 = acac.getBean(ClientBean.class);
//        int count2 = clientBean2.logic();
//        Assertions.assertThat(count2).isEqualTo(2);
//
//    }

//    /** 싱글톤 내부에서의 프포토타입을 사용하기 윙해서는 스프링에서 준비된 프로바이더 빈을 사용해야합니다.
//     *  프로바이더 빈 => ObjectFactory, ObjectProvider
//     */
//    @Scope("singleton")
//    static  class ClientBean{
//        private final PrototypeTestBean prototypeBean;
//        // 핵심 코드
//        @Autowired
//        private ObjectProvider<PrototypeTestBean> prototypeTestBeansProvider;
//
//        @Autowired
//        public ClientBean(PrototypeTestBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public  int logic(){
//            // 핵심 코드 2
//            PrototypeTestBean prototypeTestBean = prototypeTestBeansProvider.getObject();
//            prototypeTestBean.addCount();
//            int count = prototypeTestBean.getCount();
//            return count;
//        }
//
//    }
//
//    @Test
//    void  singletonClientUsePrototype() {
//        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeTestBean.class);
//        // 1. 클라이언트 빈 가져오기
//        ClientBean clientBean1 = acac.getBean(ClientBean.class);
//        int count1 = clientBean1.logic();
//        Assertions.assertThat(count1).isEqualTo(1);
//
//        // 2. 두번재 호출하기
//        ClientBean clientBean2 = acac.getBean(ClientBean.class);
//        int count2 = clientBean2.logic();
//        Assertions.assertThat(count2).isEqualTo(1);
//
//    }

    /** Javax.inject:javax.inject:1 을 사용하기
     *  gradle에 해당 implement 추가
     *  ObjectProvider를 그래들 라이브러리 추가한 Provider로 변환
     */
    @Scope("singleton")
    static  class ClientBean{
        private final PrototypeTestBean prototypeBean;
        // 핵심 코드 : ObjectProvider > Provider ( Javax.inject꺼 )
        @Autowired
        private Provider<PrototypeTestBean> prototypeTestBeansProvider;

        @Autowired
        public ClientBean(PrototypeTestBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public  int logic(){
            // 핵심 코드 2 prototypeTestBeansProvider.getObject() > prototypeTestBeansProvider.get()
            PrototypeTestBean prototypeTestBean = prototypeTestBeansProvider.get();
            prototypeTestBean.addCount();
            int count = prototypeTestBean.getCount();
            return count;
        }

    }

    @Test
    void  singletonClientUsePrototype() {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeTestBean.class);
        // 1. 클라이언트 빈 가져오기
        ClientBean clientBean1 = acac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        // 2. 두번재 호출하기
        ClientBean clientBean2 = acac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }
}
