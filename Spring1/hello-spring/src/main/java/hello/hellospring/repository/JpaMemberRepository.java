package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

//    라이브러리다운받으면 스프링부트가 EntityManager를 자동으로 만들어줌
    private final EntityManager em;
//    JPA는 EntityManager라는걸로 모든게 동작함 그래서 이렇게 작성하고.
//    JPA를쓰려면 EntityMAnger를 주입받아야함.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
//        이렇게하면 JPA가 Insert쿼리 만들어서 다집어넣고, id도 만들어서 다해줌.. 좋다 신기하다. sql안써도되는게 좋다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class).setParameter("name", name).getResultList();
// 이게 jpql임..
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
