package hello.core.findBeans;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;


// 스프링 빈은 부모타입으로 조회하면 자식 타입의 빈들도 모두 조회된다. 물론  부모타입이 클래스이면 부모타입도조회 -> 당근 NoUniqueBeanDefinitionException 예외발생
public class ApplicationContextBeanParentType {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestClass.class);

    @Test
    @DisplayName("부모타입으로 빈 조회")
    void findBeansParentType(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회 + 빈이름으로 조회")
    void findBeanParentTypeByBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("자식 타입으로 조회 - 좋지않음")
    void findSubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 조회 - Object")
    void findObjectType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

   @Configuration
    static class TestClass{
        @Bean
        DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
