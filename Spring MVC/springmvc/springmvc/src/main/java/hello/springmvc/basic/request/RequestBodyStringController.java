package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {


    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message body={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message body={}", messageBody);

        responseWriter.write("ok");
    }

    // HttpEntity<String> 너 문자구나 그럼 httpbody에있는데이터를 문자로 바꿔서 너한테줄게 라고 http메시지컨버터가 동작함.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders(); // 요청 헤더도 조회할슁ㅆ음 이러케!

        log.info("message body={}", messageBody);

        return new HttpEntity<>("ok");
    }

    // 끝.. 너무간단함 너무편함 - 이걸실무에서 많이씀.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("message body={}", messageBody);

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-string-v44")
    public String requestBodyStringV44(@RequestBody String data){
        log.info("message body ={}", data);
        return "ok";
    }
}
