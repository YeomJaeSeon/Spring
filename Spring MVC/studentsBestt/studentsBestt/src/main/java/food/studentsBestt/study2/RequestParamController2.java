package food.studentsBestt.study2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RequestParamController2 {

    @ResponseBody
    @GetMapping("/requestParam")
    public String requestParam(@RequestParam String userId, @RequestParam Long age){
        log.info("userId={}", userId);
        log.info("id={}", age);

        return "ok";
    }

    // @RequestParam생략가능
    @ResponseBody
    @GetMapping("/requestParamV2")
    public String requestParamV2(String userName, Long age){
        log.info("userName={}, age={}", userName, age);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/requestParamV3")
    public String requestParamV3(@ModelAttribute UserData userData){
        log.info("userName={}, age={}", userData.getUserName(), userData.getAge());
        return "ok";
    }

    @ResponseBody
    @GetMapping("/requestParamV4")
    public String requestParamV4(UserData userData){
        log.info("userName={}, age={}", userData.getUserName(), userData.getAge());
        return "ok";
    }
}
