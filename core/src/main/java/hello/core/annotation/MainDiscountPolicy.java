package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented  // 여기 까지는 기본 Qualifier 가 가지는 애노테이션
@Qualifier("mainDiscountPolicy") // Qualifier 지정 아래 애노테이션 명과 같으므로 애노테이션 기능 수행 가능
public @interface MainDiscountPolicy {
}
