package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinationName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinationName);
            System.out.println("name = " + beanDefinationName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("내 가등록한 모든 빈 출력")
    void findAppicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinationName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinationName);

            //
            // 내가 따로 등록한 Bean : beanDefinition.ROLE_APPLICATION
            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){
                Object bean = ac.getBean(beanDefinationName);
                System.out.println("name = " + beanDefinationName + " object = " + bean);
            }
        }
    }



}
