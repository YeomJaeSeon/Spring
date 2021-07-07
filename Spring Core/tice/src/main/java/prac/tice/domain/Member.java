package prac.tice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member(){}

}
