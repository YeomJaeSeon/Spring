package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 애너테이션 기반의 컨트롤러는 V2처럼 ModelAndView를 반환해도되고~
    // 이렇게 문자열 String으로 리턴해도됨 그럼 알아서 viewname으로 인식함.
//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    // 바로 이렇게 httpSErvletRequest사용안해도 쿼리파라미터 데이터를 사용가능!(애너테이션
    // 기반 컨트롤러는 이것도된다!)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
                             ){
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    // 너무간단해졌다... 진짜 애너테이션기반 컨트롤러 Spring MVC는 대박이다.
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
