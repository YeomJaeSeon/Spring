package js.com.controller;

import js.com.member.Member;
import js.com.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 회원가입

    /*
    회원가입 페이지 응답
    */
    @GetMapping("/signup")
    public String responseSignUpPage(){
        return "signup/signup";
    }

    /*
    회원가입 html form post 데이터 요청.
     */

    @PostMapping("/signup")
    public String signUp(
            @ModelAttribute Member member,
            @RequestParam String rePwd
    ){
        if(!member.getPwd().equals(rePwd)) return "signup/signup"; // 비번다름
        else{
            boolean isJoin = memberService.join(member);
            if(!isJoin){
                return "signup/signup"; // 실패하면 회원가입페이지로.
            }
        }

        log.info("가입한 회원들 members={}", memberService.showMembers());

        return "redirect:/login"; // 회원가입 성공하면 회원가입 페이지로
    }

    // 로그인 페이지 요청

    @GetMapping("/login")
    public String responseSignInPage(){
        return "login/login";
    }

    // 로그인

    @PostMapping("/login")
    public String requestLogin(
            @ModelAttribute Member member
    ){
        log.info("로그인하려고하는 member={}", member);
        if(memberService.login(member)) { // 로그인성공
            log.info("로그인 성공");
            return "redirect:/board";
        }
        log.info("로그인실패 .. ㅠ");
        return "redirect:/login";
    }

}
