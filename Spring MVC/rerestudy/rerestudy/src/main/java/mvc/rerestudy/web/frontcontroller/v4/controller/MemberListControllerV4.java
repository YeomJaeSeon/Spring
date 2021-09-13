package mvc.rerestudy.web.frontcontroller.v4.controller;

import lombok.RequiredArgsConstructor;
import mvc.rerestudy.domain.member.Member;
import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerV4 implements ControllerV4 {
    private final MemberRepository memberRepository;

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);

        return "members";
    }
}
