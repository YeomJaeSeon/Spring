package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    // pathVariable 경로변수 이거 진짜많이사용함.
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mapping PAth userId={}", data);

        return "ok";
    }

    // pathVariable여러개 사용 이렇게 변수이름과 pathVariable 변수이름이 같으면 ("userId")부분 생략가능.
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingpath uesrId={}, orderId={}", userId, orderId);

        return "ok";
    }

    // 특정 파라미터 정보도 있어야 매핑을 함. 그닌까 매핑하는 정보가 URL + 파라미터
    // 요청된 URL에 mode=debug파라미터가 있어야 이메서드 호출됨 ex)http://localhost:8080/mapping-param?mode=debug
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    //특정 헤더있어야 호출하는 것도있음
    // 요청이 특정 헤더가 있고 URL도 매핑되야 메서드호출.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    //엄청 다양한 조건으로 매핑할수있네.. 이정도만알자고

    // content-type요청오는 바디의 데이터 형식을 또 매핑정보에 넣을수있다.
    // 어차피 content-type도 헤더닌까 그냥 위에처럼 특정헤더매핑으로 사용하면안됨?
    // 그래도됨! 근데 이게 더 많은 걸 제공해주니 이거사용하자. 컨텐트 타입에 따라 다르게 처리하고싶을때는.
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
