package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    // 요청 파라미터는 @ModelAttribute로 객체로 자동으로 받았지만
    // json으로 데이터가 바디에담겨서 요청올때는 ObjectMapper(Jackson라이브러리)를 이용해서
    // json을 객체로변환해야한다.
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    //굳이 ObjectMapper사용해야해? body에 json오는 데이터도 @ModelAttribute처럼 바로 객체로 못변환함?
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    // HttpEntity 사용해도된다.
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age = {}", data.getUsername(), data.getAge());

        return "ok";
    }

    //json -> 객체 -> json
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }

    //==복습==//
    @ResponseBody
    @PostMapping("/request-body-json-vv3")
    public String requsetBodyJsonvv3(@RequestBody HelloData data){
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-vv4")
    public String requestBodyJsonvv4(HttpEntity<HelloData> httpEntity){
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-vvv4")
    public String requestBodyJsonvvv4(RequestEntity<HelloData> helloDataRequestEntity){
        HelloData data = helloDataRequestEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-vv5")
    public HelloData requestBodyJsonvv5(@RequestBody HelloData data){
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}
