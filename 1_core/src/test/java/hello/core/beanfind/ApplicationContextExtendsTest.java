package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.Map;

class ApplicationContextExtendsTest {
    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {return new RateDiscountPolicy(); }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {return new FixDiscountPolicy(); }
        // Discountpolicy로 조회하면 하위에 있는 rate와 fix 두개가 올라올 것입니다.
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘이상있으면 중복오류")
    void findByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
        // Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘이상있으면 빈이름을 지정 조회")
    void findByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }
    @Test
    @DisplayName("특정 하위 타입으로 조회 - 가능은 하지만 권장X")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("특정 부모타입으로 모두 조회")
    void findAllBeanByParentType(){ // 특정 부모타입 하위 객체
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("최상위 타입으로 모두 조회")
    void findAllBeanByObjectType(){ // Srping 자체 모든  빈객체
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
//        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }



}
