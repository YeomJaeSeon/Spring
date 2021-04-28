package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;

public class ComponentScanFilterTest {

    // 컴포넌트 스캔대상 필터링
    @Test
    void scanFilter(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // 컴포넌트 스캔 대상 필터링되었으므로 컴포넌트 스캔방식으로 스프링빈등록하려해도 스프링빈등록안됨.
        // 컴포넌트 스캔대상에서 제외된 클래스임.BeanB는.
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB", BeanB.class));

    }

    // 컴포넌트 스캔방식으로 스프링 컨테이너 에 스프링빈 자동등록하는데 @Component있다고 다등록하는게아니라 컴포넌트 스캔대상 필터링
    // 에너테이션 기준으로 필터링함.
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class FilterAppConfig{

    }
}
