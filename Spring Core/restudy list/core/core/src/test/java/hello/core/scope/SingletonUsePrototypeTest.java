package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonUsePrototypeTest {

    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class); // 매개변수로주면 컴포넌트 스캔방식으로 자동 빈등록(스프링 컨테이너 생성될때)
        Prototype prototypeBean1 = ac.getBean(Prototype.class);
        Prototype prototypeBean2 = ac.getBean(Prototype.class);
        prototypeBean1.addCount();
        int count1 = prototypeBean1.getCount();

        prototypeBean2.addCount();
        int count2 = prototypeBean2.getCount();

        assertThat(count1).isEqualTo(count1).isEqualTo(1); // prototype빈이므로 빈조회할때마다 새로운 빈을 생성 - 당연한것

        
    }

    @Test
    @DisplayName("싱글톤 빈 스코프에서 프로토타입 빈을 사용하면?")
    void singletonUsePrototypeBeanTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class ,SingletonClass.class); // 이떄 SingleTonClass스프링빈은 프로토타입 빈의 의존관계 주입을 마친다.
        // 즉, 싱글톤 스코프 빈은 이때 생성자 주입으로 다른 빈인 (스프링 컨테이너 내의) PrototypeClass Bean의 의존관계 주입을 마친다.
        // 즉, 싱글톤 스코프 빈이 프로토타입 빈을 사용하는건 의존관계 주입을 마친 프로토타입 빈을 계~속 사용하는것이다. 프로토타입 빈 조회할떄마다 새로운 빈을 받는게아니다.
        //아래 테스트 결과를보자
        SingletonClass clientA = ac.getBean(SingletonClass.class);
        int count1 = clientA.logic();
        assertThat(count1).isEqualTo(1);

        SingletonClass clientB = ac.getBean(SingletonClass.class);
        int count2 = clientB.logic();
        assertThat(count2).isEqualTo(1);
        // ??? 1이되야하는거아닌가? 과정은 모르겠고~ 프로토타입 빈을 사용햇잖아. 그럼 조회할때마다 새로운 빈(객체)를 생성하기떄문에 count는 1이되야하는거아니야?
        // 아니다. 싱그롵ㄴ 빈이 의존관계 주입시 주입한 프로토타입 빈(객체)는 이미 주입완료된 하나만 재사용하는것이다. 그래서 하나의 빈(객체)에 대한 필드를 변경하는것이므로 1이아니라 2가된다.
        // 근데 프로토타입 빈은 조회할떄마다 새로운 빈이 등록되라고 사용하는거잖아... 이러면 의미없잖아 ㅠㅠ
        // 맞아! 의미없어 그래서 해결방법이있다.
        // 일단 그전에 코드를 좀변경해서 싱글톤 빈내에서 프로토타입빈이 사용될ㄸ마다 새로운 객체를 생성하도록 바꿔보자


    }

    static class SingletonClass{
        @Autowired
        private ApplicationContext applicationContext;

        // 생성자주입으로 의존관계주임 생성자하나이므로 @Autowired생략가능 그런데 쓰겠다
//        @Autowired
//        public SingletonClass(Prototype prototype){
//            this.prototype = prototype;
//        }

        public int logic(){
            Prototype prototypeBean = applicationContext.getBean(Prototype.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

// 프로토타입 빈 - 초기화콜백까지 스프링컨테이너가관리 그이후는 클라이언트코드에 책임던짐 , 클라이언트는 조회할떄마다 새로운 빈을 받음
    @Scope("prototype")
    static class Prototype{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("Prototype.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Prototype.destroy");
        }

    }
}
