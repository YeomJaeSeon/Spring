package hello.springmvc.restudy.requestParam;

import hello.springmvc.restudy.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 쿼리파라미터요청
 * get -쿼리파라미터
 * post - html form -바디에 쿼리파라미터
 *
 * @RequestParam 혹은 @ModelAttribute
 * 둘다 생략가능
 * @RequestParam은 기본형올때 생략가능, @ModelAttribute는 객체 타입 생략가능
 *
 */
@Slf4j
@RestController
@RequestMapping("/lol")
public class RequestParamTest {

    @GetMapping("/v1")
    public String getParam1(@RequestParam String name, @RequestParam int level){
        log.info("v1 name : {}, level : {}", name, level);
        return "ok";
    }

    @GetMapping("/v2")
    public String getParam2(String name, int level){
        log.info("v2 name : {}, level : {}", name, level);
        return "ok";
    }

    // @ModelAttribute
    @PostMapping("/v1")
    public String getParam3(@ModelAttribute Champion champion){
        log.info("v3 name : {}, level : {}", champion.getName(), champion.getLevel());
        return "ok";
    }

    @PostMapping("/v2")
    public String getParam4(Champion champion){
        log.info("v4 name : {}, level : {}", champion.getName(), champion.getLevel());
        return "ok";
    }


}
