package hello.freeboard.controller.member;

import hello.freeboard.domain.Member;
import hello.freeboard.service.MemberService;
import hello.freeboard.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     * 회원가입
     * 1. 회원가입 페이지 응답(뷰템플릿)
     * 2. 회원가입 post 요청 처리
     */
    @GetMapping("/signup")
    public String responseSignUpPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession != null){
            return "redirect:/boards";
        }
        model.addAttribute("signup", "signup");
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Member member, @RequestParam String rePwd, Model model){
//        저장된 member가있는지
//        비밀번호 두개가맞는지 확인해야함.
        if(member.getUserId() == "" || member.getPwd() == ""){
            model.addAttribute("error", "아이디나 비밀번호 입력해주세요.");
            return "member/signup";

        }
        if(!member.getPwd().equals(rePwd)){
            model.addAttribute("error", "비밀번호 두개가 다릅니다.");
            return "member/signup";
        }
        if(memberService.isExistedId(member) == false) {
            model.addAttribute("error", "아이디가 이미 존재합니다.");
            return "member/signup";

        }
        model.addAttribute("success", "회원가입 성공");
        memberService.signup(member);
        return "member/signup";
    }

    /**
     * 로그인
     * 1. 로그인 페이지 응답(뷰 템플릿)
     * 2. 로그인 post요청 처리
     */

    @GetMapping("/login")
    public String responseLoginPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession != null){
            return "redirect:/boards";
        }
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, Model model, HttpServletRequest request){
        log.info("username={}, pwd={}", member.getUserId(), member.getPwd());
        if(member.getUserId() == "" || member.getPwd() == "") {
            model.addAttribute("error", "아이디나 비밀번호 입력해주세요.");
            return "member/login";
        }
        if(!memberService.loginCheck(member)) {
            model.addAttribute("error", "없는 회원이거나 아이디나 비번이 틀렸습니다.");
            return "member/login";
        }
        else {
            memberService.login(request, member);
            return "redirect:/boards"; //로그인성공
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, @RequestParam String user){
        memberService.logout(request, user);
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        log.info("로그아웃할 login={}", login);

        return "redirect:/login";
    }
}
