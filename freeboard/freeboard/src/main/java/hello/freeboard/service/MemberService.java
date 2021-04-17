package hello.freeboard.service;

import hello.freeboard.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    boolean isExistedId(Member member);
    Member signup(Member member);
    boolean loginCheck(Member member);
    void login(HttpServletRequest request, Member member);
    void logout(HttpServletRequest request, String userId);
}
