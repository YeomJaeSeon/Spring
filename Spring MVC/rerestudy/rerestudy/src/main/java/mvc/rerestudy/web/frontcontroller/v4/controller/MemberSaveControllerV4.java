package mvc.rerestudy.web.frontcontroller.v4.controller;

import lombok.RequiredArgsConstructor;
import mvc.rerestudy.domain.member.Member;
import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerV4 implements ControllerV4 {
    private final MemberRepository memberRepository;
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
