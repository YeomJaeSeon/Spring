package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입의 빈이 둘이상 있으면 중복오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타이의 빈이 둘이상 있으면 빈 이름을 지정하면 됩니다!")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        // 빈 조회할때의 타입혹은 자식 타입의 빈을 모두 조회한다. (MemberRepository.class)타입으로 조회하면 MemberRepository거나 MemoryMemberRepository타입의 빈 조회. 근데 MemoryRepository는 인터페이스임.
        Map<String, MemoryMemberRepository> beans = ac.getBeansOfType(MemoryMemberRepository.class);
        for (String key : beans.keySet()) { // keySet은 Map의 key들을조회..!
            System.out.println("key = " + key + " value = " + beans.get(key));
        }
        System.out.println("beans = " + beans);
        assertThat(beans.size()).isEqualTo(3);
    }

    // 스프링 컨테이너 설정 클래스(자바설정클래스, XML도가능..) - 이 설정정보를 보고 스프링 빈이등록되고 스프링빈간의 의존관계도 설정된다.
    @Configuration
    static class SameBeanConfig {
        // 이 메서드의 이름이 스프링 빈의 이름(key)이고 호출되어 반환되는 값이 스프링 빈의 value이다.
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemoryMemberRepository memberRepository3(){
            return new MemoryMemberRepository();
        }
    }
}
