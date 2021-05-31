package restudy.gogogo.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.gogogo.AutoAppConfig;
import restudy.gogogo.service.MemberService;
import restudy.gogogo.serviceImpl.MemberServiceImpl;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
