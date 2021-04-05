package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    // 위에 클래스레벨에서 @RestController로 뷰를 찾고 랜더링하는 방식으로 응답안하고
    //바디에 데이터를콱 넣어서 응답하게끔할수있는데 그냥 이렇게
    //메서드 레벨에서 @ResponseBody 애너테이션을 사용해도 위기능을한다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username ={}, age = {}", memberName, memberAge);

        // 컨트롤러에서 String을 반환하면 기본적으로 @Controller면 뷰를 찾고 렌더링한다.
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // ("username")생략
            @RequestParam int age
    ){
        log.info("username ={}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username ={}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //required=true : 기본값
            @RequestParam(required = false) Integer age){

        log.info("username ={}, age = {}", username, age);

        return "ok";
    }

    // defaultValue있으면 사실상 required는 필요가없음.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //required=true : 기본값
            @RequestParam(required = false, defaultValue = "-1") int age){

        log.info("username ={}, age = {}", username, age);

        return "ok";
    }

    // Map으로 그냥 key value로 받을수있음 간단하게.
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
            ){

        log.info("username ={}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    // 요청파라미터에 대해서 실제개발에선 이렇게 객체로 변환해준다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @RequestParam String username,
            @RequestParam int age
    ){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("data ={}", helloData);
        log.info("username ={}, age = {}", helloData.getUsername(), helloData.getAge());


        return "ok";
    }

    // 이렇게 한번에 객체로 딱받을수있다. 이거 미쳤따리.
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        log.info("data ={}", helloData);
        log.info("username ={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    // @ModelAttribute 생략할슁ㅆ음. @RequestParam생략한거처럼.
    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData){
        log.info("data ={}", helloData);
        log.info("username ={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
