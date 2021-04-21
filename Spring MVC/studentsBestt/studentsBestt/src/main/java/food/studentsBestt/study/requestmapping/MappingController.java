package food.studentsBestt.study.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 스프링 빈중의 클래스레벨에 @Controller나 @RequestMapping이있으면 애너테이션기반 컨트롤러로 인식
public class MappingController {
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    // @RestController이므로 리턴값을 뷰리졸버로 물리적인 viewName으로 바꾸서ㅓ 뷰를렌더링하는게아나ㅣㄴ
    // 응답 바디에 데이터 콱넣고 내려줌.
    @GetMapping("/mappoing-get-v1")
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    // 경로변수 @PathVariable사용
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("data={}", userId);
        return "ok";
    }
}
