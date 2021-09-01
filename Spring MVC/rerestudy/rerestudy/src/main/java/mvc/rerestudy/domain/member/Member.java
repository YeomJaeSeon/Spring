package mvc.rerestudy.domain.member;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Member {
    @Id @GeneratedValue
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private int age;
}
