package hello.core.mytest;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DoubleBean {

    @Test
    void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DoubleBeanTest.class);
        DiscountPolicy bean = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);

    }


    static class DoubleBeanTest{

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
