# 지금까진 DB만들고 자바로 DB접근하기위해 순수 Jdbc를 써봤고 너무복자하니 스프링에서 제공하는 JdbcTemplate으로 DB접근해보았따. Jdbc는 쿼리를 날려줘야하는데 그럴필요가없는 JPA를 알아보자!! (자바로 DB접근하는 목적은 같으나 방법은다르다.)

- JPA라는 기술을 쓰면 개발자가 SQl을 작성안해도된다.(JPA가 자동으로 sql쿼리를 처리해줌. -> 개발생산성높여줌)

- JPA를 사용하면 sql, 데이터중심에서 객체중심으로 우리가 개발할수있다. -> 개발생산성 크게높임.

- JPA라는건 인터페이스만 제공되는것이다. 구현체로 hibernate .. 이런것들이있다. 우리는 hibernate만쓸거임

- JPA는 객체랑 ORM이라는기술 (Object Relational Mapping) 객체와 관계형 db테이블을 맵핑한다(어노테이션으로)

- Entity데이터를 지정한다.(우리는 도메인 비지니스 객체); - DB와 매핑됨.

- JPA를 사용하려면 EntityManager라는 녀석을 사용해야한다.그래서 이녀석을 주입받는다.

- JPA(Hibernate)를 사용하면 Insert select..같은 쿼리안쓰고 알아서해줌..

- 그래도 PK의 값에 대한 DB를 처리하는게 아니면 jpql같은 sql이랑 비슷한 문법을 통해서 사용한다.

- JPA사용하면서 하나 주의할점은 항상 @Transactional이있어야함. 어 이건 통합테스트때 테스트하고 DB에 커밋안해서 데이터 반영안하는 어노테이션이였짜낭!. 근데 항상 데이터베이스에 데이터 쿼리스트링 날릴땐 트랜잭션해야함.!

- **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.**

- JPAMemberRepository 생성하고 설정파일에서 스프링 설정만 JPAMemberRepository로 변경하자! - 끝!
