package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2); // 1이아니고 2네?

    }

    @Scope("singleton")
    @RequiredArgsConstructor // 생성자주입으로 의존관계 주입받는다.
    static class ClientBean{
        private final PrototypeBean prototypeBean; // 생성시점에 주입이되어버림(프로토타입 빈을 주입받음)

//        @Autowired // 스프링컨테이너에있는 스프링빈을 가져와 의존관계주입.
//        private ApplicationContext applicationContext;  참고로 요건 의존관계 주입하는게아니라 스프링 관리하는 컨테이너에 대해서 스프링빈을 찾게해주는 것이다. 그냥 요렇게 하는방법이있다정도면된다.

        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); // 로직호출할떄 프로토타입 빈을 조회하므로 새로운 빈을 계속해서 생성
            prototypeBean.addCount();
            int count = prototypeBean.count;
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
