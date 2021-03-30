package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v1하위에들어오면 어떻게들어오던 (자식의 자식으로들어오던)무조건 이 서블릿 호출됨.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //컨트롤러 매핑정보
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    //생성자에서 컨트롤러 매핑정보 생성
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 부모클래스타입의 참조변수가 자식클래스의인스턴스 타입 참조가능. - 다형성
        // == ControllerV1 controller = new MemberListControllerV1()이거랑 똑같음.
        ControllerV3 controller = controllerMap.get(requestURI); // 인터페이스로꺼내게됨.
        if(controller == null){ // 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //프론트 컨트롤러 서블릿에서 쿼리파라미터 데이터를 담은Map인 paramMap을만들어서
        // 각 컨트럴로로 넘겨줘야함.(각 컨트롤러는 서블릿 종속성을 없앴으므로 request.getPAramter()를 사용못하지?)
        // 프론트컨트롤러만 서블릿에 종속되어있고 나머지 컨트롤러는 서블릿에종속되어있지않으므로
        // 쿼리파라미터의 데이터를 paramMap에 넣음 이건 컨트롤러에 인자로 전달될것.
        Map<String, String> paramMap = createParamMap(request);
        // 인터페이스를 통해서 구현체를 공통의 로직으로 뽑아낼수있따.
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        //mv.getViewName()인 논리적인 경로를 viewResolver함수를 통해서 물리적인 경로로변경
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
