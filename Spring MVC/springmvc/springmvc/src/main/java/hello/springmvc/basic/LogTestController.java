package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// @Controller와 다른건 @Controller는 반환할때 viewName이반환되지만 @RestController는
// 반환값이 그냥 뷰리졸버 거치고~ 이런게없이 그냥 String반환해버림.
// 응답 바디에 그냥 데이터 싹 넣어버리기만함
@Slf4j // 애너테이션으로 롬복이 자동으로 아래 log만드는 코드 넣어줌.
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    // @RequestMapping 인데 GET HTTP메서드만 .
    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);

//        log.trace("trace log= " + name);
        //이런시긍로 절대 사용하며안됨 절대로! + 로그 찍을때 +연산자 사용하면안됨
        // +연산자는 연산이되므로 리소스를 사용하는 것이다.
        //그런데 지금 이 패키지 로그레벨을 debug로해서 trace로그는 안찍힘
        // 근데!!! 리소스를낭비하고있네 그래서 무조건 로그찍을때 +연산자사용하지말고 메서드호출로 해라.

        // 로그레벨 (기본이 info)임.

        log.trace("trace log={}", name);
        log.debug("debug log ={}", name);
        log.info("info log ={}", name);
        log.warn("warn log ={}", name);
        log.error("error log ={}", name);

        return "ok";
    }
}
