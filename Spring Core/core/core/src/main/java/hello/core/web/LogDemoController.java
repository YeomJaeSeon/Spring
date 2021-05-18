package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    // ObjectProvider는 스프링 컨테이너에서 원하는 빈을 조회해서 가져올수있다. ApplicationContext.getBean("~"); 역할이다. - 외부에서 의존관계를 주입 받는게아닌 직접 주입하므로 DL이라한다. Dependency Look Up
    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger빈 의존관계 주입하는게아닌
    // MyLogger빈 Look up할수있는 객체가 주입이된다.(DL)
    // 스프링 컨테이너에서 해당 빈을 주입하는게아닌 컨테이너에서조회할수있는 Look Up 객체가 주입됨 그닌까 스프링컨테이너에서 request 스코프 빈이 없어서 에러가났는데 그럴일은없겠다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString(); // 이 메서드가 호출된것은 HTTP 요청이 온것이고 그러면 이미 request스코프 빈이 살아있다.
        // 그말인 즉슨!! 스프링컨테이너에 request 스코프 빈이 존재하기 때문에 DL이 가능하므로 제대로 꺼낼수있따. (ObjectProvider)로
        MyLogger myLogger = myLoggerProvider.getObject(); // 직접 꺼내기 - DL - 이때 스프링 컨테이너에서 request 스코프 빈인 MyLogger 객체를 조회해서 가져온다.
        // 있으면 가져오고 없으면 생성해서 가져오는데, HTTP요청진행중이므로 request스코프빈이 생성가능하므로 이때 request 스코프 빈인 MyLogger 객체가 생성된다.

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller Test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
