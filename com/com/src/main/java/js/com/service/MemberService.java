package js.com.service;

import js.com.member.Member;
import js.com.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입 비즈니스로직.
    public boolean join(Member member){
        if(isValidationJoin(member)) {
            memberRepository.save(member);
            return true; // 회원가입성공
        }
        return false; // 회원가입 실패.
    }

    /*
    이미 존재하는 아이디있는지 검사하는 비즈니스 로직
     */
    public boolean isValidationJoin(Member member){
        Vector<Member> members = memberRepository.findAll();
        for (Member checkMember : members) {
            if(member.getId().equals(checkMember.getId())) return false; // 중복아이디 존재
        }
        return true;
    }

    public boolean login(Member member){
        Vector<Member> members = memberRepository.findAll();
        for (Member checkMember : members) {
            if(member.getId().equals(checkMember.getId()) && member.getPwd().equals(checkMember.getPwd())) return true;// 로그인 성공
        }
        return false; // 로그인 실패1
    }

    public Vector<Member> showMembers(){
        Vector<Member> members = memberRepository.findAll();
        return members;
    }
}
