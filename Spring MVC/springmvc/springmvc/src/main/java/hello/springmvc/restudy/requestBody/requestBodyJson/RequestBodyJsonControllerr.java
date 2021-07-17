package hello.springmvc.restudy.requestBody.requestBodyJson;

import hello.springmvc.restudy.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/n")
public class RequestBodyJsonControllerr {

    @PostMapping("/v1")
    public String requestBodyJsonV1(HttpEntity<Champion> championHttpEntity){
        Champion data = championHttpEntity.getBody();
        log.info("v1 name : {}, level : {}", data.getName(), data.getLevel());
        return "ok";
    }

    @PostMapping("/v3")
    public String requestBodyJsonV3(RequestEntity<Champion> championRequestEntity){
        Champion data = championRequestEntity.getBody();
        log.info("v1 name : {}, level : {}", data.getName(), data.getLevel());
        return "ok";
    }

    @PostMapping("/v2")
    public String requestBodyJsonV2(@RequestBody Champion champion){
        log.info("v1 name : {}, level : {}", champion.getName(), champion.getLevel());
        return "ok";
    }
}
