package hello.core.scope;

import hello.core.lifecycle.NetworkClient;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonScopeTest {


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

    @Test
    public void singletonScopeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonTestBean.class);
        SingletonTestBean bean1 = ac.getBean(SingletonTestBean.class);
        SingletonTestBean bean2 = ac.getBean(SingletonTestBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isSameAs(bean2);
        ac.close();
    }


}
