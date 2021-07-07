package prac.tice.auto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import prac.tice.AutoAppConfig;
import prac.tice.service.MemberService;

public class AutoAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan(){

    }
}
