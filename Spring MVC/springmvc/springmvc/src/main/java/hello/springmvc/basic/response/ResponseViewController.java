package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "Hello!!");

        return mav;
    }

    //@ResponseBody하면 viewName반환하는게아닌 데이터를 그냥 응답바디에넣어서 내려줘버림
    // 뷰렌더링안함. 알지! @RestCOntroller도 마찬가지.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "HELLO!!");

        return "response/hello";
    }

    //영한형님 권장하지않는 방법 - 요청매핑경로와 뷰의 논리이름이 같으면 void로 아무것도 반환안해도
    // 알아서템플릿엔진 반환해줌.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "helloㅠㅠ");
    }
}
