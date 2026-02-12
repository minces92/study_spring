package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AutoAppConfigTest {

    @Test // 분명 AUtoAppConfig에는 아무것도 지정 없이 그냥 어노테이션만 추가되어있따.
    void basicScan (){
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);

        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
    }

}
