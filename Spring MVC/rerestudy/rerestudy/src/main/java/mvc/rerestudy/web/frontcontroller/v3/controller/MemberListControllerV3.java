package mvc.rerestudy.web.frontcontroller.v3.controller;

import lombok.RequiredArgsConstructor;
import mvc.rerestudy.domain.member.Member;
import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.ModelView;
import mvc.rerestudy.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository;
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
