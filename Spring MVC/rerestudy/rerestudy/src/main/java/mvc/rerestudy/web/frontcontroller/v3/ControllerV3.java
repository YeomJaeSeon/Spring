package mvc.rerestudy.web.frontcontroller.v3;

import mvc.rerestudy.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
