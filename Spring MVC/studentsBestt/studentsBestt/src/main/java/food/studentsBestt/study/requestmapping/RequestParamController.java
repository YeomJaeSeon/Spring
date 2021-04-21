package food.studentsBestt.study.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

    @ResponseBody
    @RequestMapping("/request-param-v1")
    public String requestParamV1(
            @RequestParam String username, int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-model")
    public String requestParamModel(
            @ModelAttribute HelloData helloData
    ){
        log.info("helloData = {}", helloData);
        return "ok";
    }
}
