package hello.freeboard.service;

import hello.freeboard.domain.Member;
import hello.freeboard.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    //final상수도 생성자로 초기화가능!

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 이미 회원인지 아닌지 확인
    public boolean isExistedId(Member member){
        List<Member> existedMembers = memberRepository.findAll();
        for (Member existedMember : existedMembers) {
            if(member.getUserId().equals(existedMember.getUserId()))
                return false; // 중복아이디 있음. | 회원임
        }
        return true; // 중복아이디없음. | 회원아님
    }


    public Member signup(Member member){
        Member saveMember = memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        log.info("members={}", members);
        return saveMember;
    }

    // 로그인 체크
    public boolean loginCheck(Member member){
        List<Member> savedMembers = memberRepository.findAll();
        for (Member savedMember : savedMembers) {
            if(member.getUserId().equals(savedMember.getUserId()))
                if(member.getPwd().equals(savedMember.getPwd()))
                return true; //로그인성공
        }
        return false; // 로그인 실패
    }

    // 로그인
    public void login(HttpServletRequest request, Member member){
        HttpSession session = request.getSession();
        session.setAttribute("login", member.getUserId());
        session.setMaxInactiveInterval(10);
        Object login = session.getAttribute("login");
        log.info("session={}", login);
    }

    //로그아웃
    public void logout(HttpServletRequest request, String userId){
        HttpSession session = request.getSession();
        Object loginUserId = session.getAttribute("login");
        log.info("탈퇴={}", loginUserId.equals(userId));
        if(loginUserId.equals(userId)){
            session.removeAttribute("login");
            Object logoutCheck = request.getSession().getAttribute("login");
            log.info("로그아웃됏나 체크 ={}", logoutCheck);
        }
    }
}
