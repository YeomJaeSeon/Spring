package mvc.rerestudy.web.frontcontroller.v3.controller;

import lombok.RequiredArgsConstructor;
import mvc.rerestudy.domain.member.Member;
import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.ModelView;
import mvc.rerestudy.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository;

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
