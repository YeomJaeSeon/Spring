package restudy.gogogo.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import restudy.gogogo.AppConfig;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.repositoryimpl.MemoryMemberRepository;
import restudy.gogogo.service.DiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanTypeTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은타입 빈 두개이상 -  중복오류 발생")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상이면 빈이름 지정 - 당연")
    void findBeanByName(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상이면 빈이름 지정 - 당연")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + " value = " + beansOfType.get(s));
        }
        System.out.println("beansOfType = " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }

    }
}
