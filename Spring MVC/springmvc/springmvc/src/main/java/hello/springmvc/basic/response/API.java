package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


// HTTP API, REST API방식.
// api만들때 디게많이사용하는 대표예제
@Slf4j
@RestController
public class API {

    // Body에 데이터넣어서 응답하는데 객체 반호나하면 메시지 컨버터가 json으로변환해줌.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api-example")
    public String giveJson(){
        HelloData helloData = new HelloData();
        helloData.setUsername("yeom");
        helloData.setAge(20);

        log.info("user={}", helloData);

        return "hello";
    }
}
