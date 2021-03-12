package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// spring은 annotation 직접써줘야함
// 웹어플의 진입점이 controller임.
public class HelloController {
    
    @GetMapping("audi")
//   http요청이 /hello이면,즉 hello라는게 들어오면 이 메소드 실행해줌
    public String goAudi(Model model){
        model.addAttribute("data", "r8");
        return "car";
//        model.addAttribute("data", "spring!");
//        return "hello";
//        문자열 hello를 리턴하면 viewresolver가 template아래 'hello'.html에 해당되는 파일을 찾음
    }

    @GetMapping("bmw")

    public String goBmw(Model model){
        model.addAttribute("data", "bmw a8");
        return "car2";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
//    응답 body(Http Response Body)에 이내용을 넣겠다는 내용임. - 뷰 이런게없음
//    이렇게 하면 그냥 이 데이터만 전달함.. 템플릿엔진과 다른점은 템플릿엔진은 뷰가 존재하는 곳에 데이터를 전달해서
//    뷰를 조작하지만 api방식은 그대로 데이터만 전달함. 페이지 소스보기 보면차이느낄수있다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

//    API방식
//    뭔가 데이터를 줘야하는 상황에서 사용하는 진짜 임 아래 예제가
//    아하 객체를 넘기면 JSON데이터로 데이터를 전달하는구나..
//    옛날엔 XML사용, 무거워서 가벼운 JSON을사용함. 요즘엔 JSON이용. 그래서 스프링도
//    default가 JSON임.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // ctrl shift enter치면 자동완성
        hello.setName(name);
        return hello;
//        처음으로 문자가 아닌 객체를 리턴함.
    }

//    static클래스로 만들면 클래스안에서 클래스 작성가능(자바 문법)
    static class Hello {
        private String name;

//        프로퍼티 접근방식, getter setter로 변수접근.
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
