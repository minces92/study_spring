package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeScopeTest {

    @Scope("prototype")
    static  class PrototypeTestBean
    {
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
    }

    @Test
    public void prototypeScopeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeTestBean.class);
        System.out.println("find Prototype bean1");
        PrototypeTestBean bean1 = ac.getBean(PrototypeTestBean.class);
        System.out.println("find Prototype bean2");
        PrototypeTestBean bean2 = ac.getBean(PrototypeTestBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }


}
