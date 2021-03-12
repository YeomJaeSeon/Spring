package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컨트롤러를 만들면(컨트롤러 annotation이 있으면) 스프링 컨테이너(스프링이 뜰때 스프링 컨테이너라는 통이뜸)
// 아래 MemberController를 스프링 컨테이너에 넣어두고 스프링이 관리함
// 이걸 스프링 컨테이너에서 스프링 빈이 관리된다고 말한다.
@Controller
public class MemberController {

    //    private final MemberService memberService = new MemberService();
//    이렇게 new를 생성해서 사용할수도있다..
//    결론부터말하면 스프링이 관리하게되면 스프링컨테이너에 등록을하고 스프링컨테이너에서
//    받아서쓰도록 바꿔야함.(new를 안쓰고)
//    MemberController에 있는 MemberService는 다른 컨트롤러에서도 사용될수있다.
//    예를들면, 주문 컨트롤러에서 이 회원 관리부분(MemberService)이 필요할수도있으닌까.
//    그러므로 여러개 만들필요가없음. 하나의 MemberService만 만들어서 다같이 공용으로 쓰면
//    됨.
//    그래서 이렇게 new를쓰는것보단 스프링 컨테이너한테 등록을 하고 쓰면됨. 스프링컨테이너엔
//    딱 하나만등록이된다.(부가적인 효과들이 많음)

    //    스프링 컨테이너에 등록하는 법 - 생성자로 해주면된다.
    private final MemberService memberService;

//  autowired도 사용
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
//  이 MemberController는 스프링 컨테이너가 뜰때 생성을 한다. 그러면 그때 이 생성자 호출.
//    생성자에 @Autowired가 있으면 MemberService를 스프링이 스프링컨테이너에 등록되어
//    있는 MemberService를 가져다가
//    연결을 시켜준다. 즉, new처럼 새로 하나 더 만드는게 아니다. 가져다가 쓰는것이다.

//    일반적으로url치는건 get방식임. (@GetMapping)
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

//    클라이언트에서 post방식은 데이터를 폼에넣어서 전달할때
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
//        우리는 spring이 MemberForm에 setName으로 설정한 name을 getName으로 꺼낸거다.

//        System.out.println("member : " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
