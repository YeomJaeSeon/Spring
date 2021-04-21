package food.studentsBestt.study2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // @ResponseBody + @Controller  -
public class RequestMapping {

    @org.springframework.web.bind.annotation.RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mappingGetV2");
        return "ok";
    }

    @GetMapping("/mapping-get-v3/{userName}")
    public String mappingPathVariable(@PathVariable("userName") String name){
        log.info("username={}", name);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/items/{itemId}")
    public String mappingPathVariableMulti(@PathVariable String userId, @PathVariable String itemId){
        log.info("userId ={}, itemId={}", userId, itemId);
        return "ok";
    }
}
