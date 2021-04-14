package hello.core.member;

public class Member {
    private Long id;
    private String memberName;
    private Grade grade;

    public Member(Long id, String memberName, Grade grade) {
        this.id = id;
        this.memberName = memberName;
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
