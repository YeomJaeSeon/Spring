package mvc.rerestudy.web.frontcontroller.v3;

import mvc.rerestudy.domain.member.Member;
import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.ModelView;
import mvc.rerestudy.web.frontcontroller.MyView;
import mvc.rerestudy.web.frontcontroller.v2.controller.MemberFormControllerV2;
import mvc.rerestudy.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3(@Autowired MemberRepository memberRepository) {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3(memberRepository));
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

        if(controllerV3 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParam(req);
        ModelView mv = controllerV3.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel() ,req, resp);
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
