package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // V3와다르게 모델을 리턴하지않네. 프론트컨트롤러에서 모델 만들어줄것이다.
        return "new-form";
    }
}
