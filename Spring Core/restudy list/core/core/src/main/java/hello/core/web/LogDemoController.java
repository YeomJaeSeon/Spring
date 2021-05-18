package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final ObjectProvider<MyLogger> myLoggerProvider;
    // 스프링 컨테이너에 없는 빈이다. request 빈스코프는 HTTP요청이들어오고 나갈떄까지 존재하므로 스프링 컨테이너에서 관리하지않는중..
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String request(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject(); // 이때 (HTTP요청이 진행중이므로) request bean이 생성된다.
        myLogger.setRequestURL(requestURL);
        myLogger.log("test Controller");
        logDemoService.logic("testId");
        return "OK";
    }


}
