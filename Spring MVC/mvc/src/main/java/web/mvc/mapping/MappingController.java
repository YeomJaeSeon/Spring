package web.mvc.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping("hello-basic")
    public String mappingBasic(){
        log.info("mapping-basic");
        return "ok";
    }

    @GetMapping("hello-basic/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("userId={}", userId);
        return "ok";
    }

    @GetMapping("hello-basic/{userId}/orders/{orderId}")
    public String helloBasic(@PathVariable String userId, @PathVariable String orderId){
        log.info("userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume(){
        log.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce(){
        log.info("mappingProduce");
        return "ok";
    }
}
