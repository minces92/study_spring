package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return  new StatefulService();
        }
    }


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService ss1 = ac.getBean(StatefulService.class);
        StatefulService ss2 = ac.getBean(StatefulService.class);

//        // [A-1]ThreadA : 사용자 A 가 10000 주문
//        ss1.order("userA", 10000);
//        // [B-1]ThreadB : 사용자 B 가 20000 주문
//        ss2.order("userB", 20000);
//
//        // [A-2] ThreadA : 사용자A 주문 금액 조회 (A-2 수행해야는데 B-1 요청이 도착)
//        int price = ss1.getPrice();
//        System.out.println("price ss1 = " + price);

        // [A-1]ThreadA : 사용자 A 가 10000 주문
        int userAPrice = ss1.order("userA", 10000);
        // [B-1]ThreadB : 사용자 B 가 20000 주문
        int userBPricee = ss2.order("userB", 20000);

        // [A-2] ThreadA : 사용자A 주문 금액 조회 (A-2 수행해야는데 B-1 요청이 도착)
//        int price = ss1.getPrice();
        System.out.println("price ss1 = " + userAPrice);
    }

}