package restudy.gogogo.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import restudy.gogogo.domain.Member;
import restudy.gogogo.repository.MemberRepository;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);


    }

    static class TestBean{

        // 스프링컨테이너에 관리되는 빈이 없을때. 자동의존관계 주입하려는 빈이 없을때..!(주입하려는 빈이 없을때.)

        //== required설정으로 false를 하면된다. - 아애 메서드자체가 호출안딤 ==//
        @Autowired(required = false)
        public void setNoBean1(MemberRepository memberRepository){
            System.out.println("memberRepository = " + memberRepository);

        }

        //== @Nullable
        @Autowired
        public void setNoBean2(@Nullable MemberRepository memberRepository){
            System.out.println("memberRepository = " + memberRepository);
        }

        //== Optional 사용 - Optional.empty()로넣어줌.
        @Autowired
        public void setNoBean3(Optional<MemberRepository> memberRepository){
            System.out.println("memberRepository = " + memberRepository);
        }
    }

}

/**
 * 스프링 컨테이너에서관리되는 빈이 없을때 해당 빈을 주입하려할떄.
 * 없는 빈을 주입하려할때 사용하는 세가지방법
 * 1. required = true
 * 2. @Nullable
 * 3. Optional
 *
 *  이럴경우가존재함.생성자 주입으로 자동의존관계주입하려하는데 멤버변수 하나는 스프링 빈을 주입하는게 아니다. 이런경우가 있을수있따.
 *  충분히
 */
