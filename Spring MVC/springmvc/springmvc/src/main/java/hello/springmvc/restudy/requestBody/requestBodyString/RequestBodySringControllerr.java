package hello.springmvc.restudy.requestBody.requestBodyString;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v")
public class RequestBodySringControllerr {

    @PostMapping("/v1")
    public String requestBodyStringV1(HttpEntity<String> data){
        log.info("v1 data={}", data.getBody());
        return "ok";
    }

    @PostMapping("/v2")
    public String requestBodyStringV2(RequestEntity<String> data){
        log.info("v2 data={}", data.getBody());
        return "ok";
    }

    @PostMapping("/v3")
    public String requestBodyStringV3(@RequestBody String data){
        log.info("v2 data={}", data);
        return "ok";
    }
}
