package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v1하위에들어오면 어떻게들어오던 (자식의 자식으로들어오던)무조건 이 서블릿 호출됨.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //컨트롤러 매핑정보
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    //생성자에서 컨트롤러 매핑정보 생성
    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("front controller");

        String requestURI = request.getRequestURI();

        // 부모클래스타입의 참조변수가 자식클래스의인스턴스 타입 참조가능. - 다형성
        // == ControllerV1 controller = new MemberListControllerV1()이거랑 똑같음.
        ControllerV1 controller = controllerV1Map.get(requestURI); // 인터페이스로꺼내게됨.
        if(controller == null){ // 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 다형서응로 오버라이딩된 메서드를 호출할것이다.
        controller.process(request, response);
    }
}
