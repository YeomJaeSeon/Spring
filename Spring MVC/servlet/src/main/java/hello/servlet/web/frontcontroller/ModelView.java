package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//@Getter @Setter 롬복사용해도됨.
// Spring MVC에는 Model&view라는게 있다.
// ModelView는 모델과 view(논리적인)이름의 멤버변수를 가지고있는 클래스임.
public class ModelView {
    private String viewName; // 논리적인 view이름
    private Map<String, Object> model = new HashMap<>();
    // 이 모델에는 member나 members 객체가 들어올것이다. key , value형태로 들어감.

    public ModelView(String viewName){
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
