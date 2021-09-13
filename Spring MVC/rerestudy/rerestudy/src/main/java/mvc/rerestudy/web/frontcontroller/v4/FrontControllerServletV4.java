package mvc.rerestudy.web.frontcontroller.v4;

import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.MyView;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberListControllerV4;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4(@Autowired MemberRepository memberRepository) {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4(memberRepository));
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);
        if(controllerV4 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParam(req);
        Map<String, Object> model = new HashMap<>();

        String viewName = controllerV4.process(paramMap, model);
        MyView view = viewResolver(viewName);
        view.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParam(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

        return paramMap;
    }
}
