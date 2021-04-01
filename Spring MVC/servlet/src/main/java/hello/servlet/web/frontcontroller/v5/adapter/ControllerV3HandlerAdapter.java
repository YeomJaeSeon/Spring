package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InaccessibleObjectException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // 참조변수 형변환 여부 확인
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, InaccessibleObjectException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        // 핸들러 어뎁터(handle메서드)에서 진짜 컨트롤러 ex)MemberFormControllerV3 의 process메서드를 호출한다.
        // 그닌까 프론트 컨트롤러에서 바로 컨트롤러를 호출하는 것과는 다르게 어댑터를 거쳐서 호출한다. 이렇게되면
        // 어댑터라는 기능을 이용하여 MemberFormControllerV3든 MemberFormControllerV4든 이 FrontControllerV5에서 사용할수있는형태로 변경됨.(ModelView형태로.)

        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
