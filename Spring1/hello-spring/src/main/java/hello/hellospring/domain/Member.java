package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 이제는 jpa가 관리하는 entity이다.
@Entity
public class Member {

    // 쿼리에 id를 넣는게아니라 DB에 값을 넣으면 DB에 ID를 자동으로넣어주는걸 Identity전략이라한다.
//    그래서 여기서도 IDENTITY를 넣어준다.(DB가 알아서 생성해주는걸 IDENTITY라한다.)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    요런식으로 어노테이션을 통해서 JPA가 DB를 관리하는 것이다.

    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}