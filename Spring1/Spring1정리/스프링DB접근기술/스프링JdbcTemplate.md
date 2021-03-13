# Jdbc Template는 순수 Jdbc를 할때와 다르게 반복코드를 제거해준다.

- 그치만 SQL은 직접 작성해줘야한다.
- 실무에서도 많이씀..!

- 코드 자체를 이해하려하지말고 JdbcTemplate을 사용한게 순수 Jdbc로 DB접근하는것보다 훨씬 간단하다는걸 느끼는게 중요.(Jdbc Template같은 경우는 cheat sheet보고하면됨.)

- 추가로 생성자 주입 할때 생성자 하나면 @Autowired생략가능.

```
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
```

- 스프링 컨테이너에있는 datasource를 가져와서 쓰는거징!, 생성자주입하는 과정임.
- 그런데 생성자하나이므로 `@Autowired`생략가능.

- ... 민ㅇ라ㅓㅁ;ㅣㄴㅇ라ㅓㅁ --- Jdbc Template작성하고 전과같이 설정파일에서 스프링빈으로 등록된 녀석만 JdbcMemberRepository -> JdbcTemplateMemberRepository로 변경하고 DI하면됨! (Spring의 장점이라했지!?)

- 새로운 객체를 스프링빈으로 등록했으니 테스트해보자. 전시간에 만들었떤 통합테스트로.

- 테스트가 진짜중요하다. 테스트코드를 잘짜는게 좋은 개발자의 덕목!
