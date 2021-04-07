package hello.freeboard.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String userId;
    private String pwd;

    public Member(){

    }
    public Member(String userId, String pwd){
        this.userId = userId;
        this.pwd = pwd;
    }
}
