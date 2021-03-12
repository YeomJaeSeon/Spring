package hello.hellospring.controller;

public class MemberForm {
    private String names;
//    요 name에 input에서 전달한 value가 들어오게됨. input의 name attribute와
//    연결되는 부분임. (spring이 setName을 통해서 넣어줌 여기에 ..)
//    이부분은 스프링이 해주는 부분임. 우리가 관여할바 x
//    form내부의 input name속성과 이 변수가 같아야한다. 그리고 이 변수이름과 setXxx가같아야한다.
//    즉, input name(name) == MemberForm 변수(name) == setXxx(setName) - setNames하면 안됨.!

    public String getName() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
