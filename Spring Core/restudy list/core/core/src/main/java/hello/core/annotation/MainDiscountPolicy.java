package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// 이렇게 직접 에너테이션을만드는이유는? -> @Qualifier로 스프링 빈 선택하는건 스펠링 하나틀리면 버그찾기가힘듬
// 그런데 이렇게 @Qualifier로 에너테이션직접만들면 컴파일시 에러르 ㄹ찾기가쉬움. 컴파일에러는 항상 좋은에러라고했당!
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
