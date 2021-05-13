package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//스코프빈 - 스프링빈이 존재할수있는범위
// prototype scope - 스프링 빈등록, 의존관계 주입, 초기화콜백까지 스프링빈이 존재함(이떄까지 스프링 컨테이너가 스프링빈을 관리함 그이후느 ㄴ클라이언트에게 던짐, 그 이후책임은 클라잉너트엑)
// 빈 조회, 클라이언트 요청할떄마다 빈을 새로 생성 - 다 다른 빈임.(싱글톤유지 X)
public class PrototypeTest {

    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBeanScope.class);
        System.out.println("find bean1");
        PrototypeBeanScope bean1 = ac.getBean(PrototypeBeanScope.class);
        System.out.println("find bean2");
        PrototypeBeanScope bean2 = ac.getBean(PrototypeBeanScope.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        ac.close();

        Assertions.assertThat(bean1).isNotSameAs(bean2);


    }

    @Scope("prototype")
    static class PrototypeBeanScope{
        @PostConstruct
        void init(){
            System.out.println("PrototypeBeanScope.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("PrototypeBeanScope.destroy");
        }
    }

}
