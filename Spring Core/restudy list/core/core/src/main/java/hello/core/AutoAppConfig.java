package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // @Configuration애너테이션 안에도 @Component가 있어서 컴포넌트 스캔 대상이된다. 그래서 아래서 제외해준것.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component가 붙은 클래스를 자동으로 스프링 빈으로 등록해줌.
public class AutoAppConfig {
}
