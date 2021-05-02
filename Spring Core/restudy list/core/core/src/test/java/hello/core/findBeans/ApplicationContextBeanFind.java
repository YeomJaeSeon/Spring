package hello.core.findBeans;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBeanFind {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 설정 파일로넘긴 자바 클래스도 스프링 빈으로 등록이됨. @Configuration썼으니 스프링 빈 이름이 @CGLIB~

    @Test
    void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("key = " + beanDefinitionName + " bean : " + ac.getBean(beanDefinitionName));
        }
    }

    @Test
    @DisplayName("내가마든 스프링 빈만 출력하기")
    void findMyBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if(ac.getBeanDefinition(beanDefinitionName).getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("key = " + beanDefinitionName + " bean : " + ac.getBean(beanDefinitionName));
            }
        }
    }

    @Test
    @DisplayName("스프링 빈 이름으로 조회하기..")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("스프링 빈 타입으로 조회하기..")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
//        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
    }

    @Test
    @DisplayName("구체 타입으로 찾기..")
    void findBeanBySpecificType(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("xxxx")
    void xxx(){
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxx", MemberService.class));
    }

    AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(TestClass.class);

    @Test
    @DisplayName("타입으로 조회시 중복일어날때.")
    void findTypeBeans(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac2.getBean(MemberService.class));
    }
    
    @Test
    @DisplayName("타입 중복시 이름으로 빈 하나고를수있음")
    void findTypeBean(){
        MemberService memberService1 = ac2.getBean("memberService1", MemberService.class);
        System.out.println("memberService1 = " + memberService1);


    }

    @Test
    @DisplayName("모든 스프링빈들 가져오기")
    void findAllBeans2(){
        Map<String, MemberService> beansOfType = ac2.getBeansOfType(MemberService.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value= " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class TestClass{
        @Bean
        public MemberService memberService1(){
            return new MemberServiceImpl(new MemoryMemberRepository());
        }

        @Bean
        public MemberService memberService2(){
            return new MemberServiceImpl(new MemoryMemberRepository());
        }
    }
}
