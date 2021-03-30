package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // ControllerV2와는다르게 서블릿기술들이 하나도안들어감. 서블릿종속성 제거.
    ModelView process(Map<String, String> paramMap);
}
