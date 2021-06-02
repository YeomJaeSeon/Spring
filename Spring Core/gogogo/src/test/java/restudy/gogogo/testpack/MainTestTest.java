package restudy.gogogo.testpack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MainTestTest {

    @Test
    @DisplayName("스프링빈 아닌걸 주입하할때.. 이럴경우가존재함")
    void hello(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainTest.class);


    }

}