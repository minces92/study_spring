package hello.core.autowired;

import hello.core.member.Member;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    static class TestBean{
        @Autowired(required = false) // true로 되어 있으면 에러 false로 되어있으면 호출이 안되서 테스트 통과
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired//(required = false) //
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);;
        }
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);;
        }

    }

    @Test
    public void testAutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
//        ac.getBean(OrderService.class);
    }
}
