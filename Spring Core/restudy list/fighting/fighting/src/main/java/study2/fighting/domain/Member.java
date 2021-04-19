package study2.fighting.domain;

public class Member {
    private String memberName;
    private Long id;
    private Grade grade;

    public Member(String memberName, Long id, Grade grade) {
        this.memberName = memberName;
        this.id = id;
        this.grade = grade;
    }
    public Member(){

    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
