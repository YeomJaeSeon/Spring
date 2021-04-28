package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan : @Component 에너테이션이 붙은 클래스를 찾아서 자동으로 스프링빈으로등록함.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 자동으로 등록되어있는 스프링빈과 이름이같으면 수동으로 등록된 스프링빈이 우선권을 갖긴한데 그냥 무조건 이름 다르게해야한다생각하자.
    // 이러면 에러잡기가 너무어려움.. 그래서 스프링부트도 최신엔 자동등록 스프링빈과 수동등록 스프링빈간의 이름 충돌을 에러로 바꿔버림
//    @Bean("memoryMemberRepository") // @Component붙은 클래스 맨앞 글자 소문자로 바뀌어서 스프링 빈 이름으로 등록됨.
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
