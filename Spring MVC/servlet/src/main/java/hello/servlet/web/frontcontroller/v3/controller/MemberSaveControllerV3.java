package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();
    //singleton 객체임. - 생성자가 private

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // paramMap에는 프론트컨트롤러에서 넣어준 쿼리파라미터 데이터가 존재함.
        // 이런건다 프론트컨트롤러 서블릿에서할거다. (하나밖에없으니 괜찮!)
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        // 논리적인 이름넣어주고
        mv.getModel().put("member", member);
        // ModelView인스턴스에는 모델도있으므로 save는 쿼리파라미터의 데이터를 가지고있는
        // member객체를 ModelView의 멤버변수인 model에 넣어준다.(paramMap과
        // ModelView의 model Map은 다르다. 전자는 쿼리파라미터 데이터가 있고 후자는
        // member같은 모델에담긴 데이터가있다.

        return mv;
    }
}
