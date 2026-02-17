package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Configuration
    static class BeanLifeCycleTestConfiguration{
//        @Bean(initMethod = "init" , destroyMethod = "close") // 생성/소멸 파라미터
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void LifeCycleTest(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanLifeCycleTestConfiguration.class);
        // 상속 순서
        // ApplicationContext > ConfigurableApplicationContext > AnnotationConfigApplicationContext
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(BeanLifeCycleTestConfiguration.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        // 이번 목표 코드 : ac.close() => ApplicationContext > ConfigurableApplicatonContext 로 변경 필요
        // configure 에서 close 까지 가져올 수 있는건 configurableApplicationContext 입니다.
        ac.close();
    }


}
