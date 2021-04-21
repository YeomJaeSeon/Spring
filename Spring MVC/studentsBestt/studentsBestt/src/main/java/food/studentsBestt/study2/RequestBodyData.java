package food.studentsBestt.study2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyData {

    // Http 메시지 컨버터가동작. HTTP API방식 바디에 HTML말고 데이터가들어갈떄 text, JSON
    // HTTP메시지컨버터는 요청받을때응답할때 둘다 양방향 모두동작
    // 요청받을때 @RequestBody or RequestEntity(HttpEntity)있으면 content-type 클래스타입보고
    // 변환 . json -> 객체로도
    @ResponseBody
    @PostMapping("/request-body-v1")
    public String requestBodyV1(@RequestBody String text){
        log.info("text={}",text);
        return "ok";
    }

    // 요것도 Http메시지컨버터가 동작해서 (HTTP API빵식) json을 객체로변환
    @ResponseBody
    @PostMapping("/request-body-json")
    public String requestBodyJson(@RequestBody UserData userData){
        log.info("userName={}, age={}", userData.getUserName(), userData.getAge());
        return "ok";
    }
}
