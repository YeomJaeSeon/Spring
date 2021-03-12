# 고대 선배들이 하던방식임!

- JDBC를 이용해서 DB와 Java(웹서버)를 연결해보자. 우리 어플 서버에서 DB에 접근할수 있다.! JDBC를통해서
- JDBC : Java DataBase Connectivity
- 즉, 자바는 기본적으로 DB와 연결되려면 JDBC 라이브러리가 꼭있어야함!
- DB를 이용할때 DB가 제공하는 클라이언트가 필요함 그게 h2databse:h2 이 라이브러리임.

# 이번에 할건 Jdbc를 이용해서 자바에서 DB접근을 가능하게 해보자.

- 막 이것저것 코드로 JdbcMemberRepository를 만듬..
- 구현체가 MemoryMemberRepository에서 JdbcMemberRepository로 변경되었다.
- 우린 설정파일(스프링빈 등록하는 방법중 자바코드로 스프링빈 등록.)에서 다른 구현체(JdbcMemberRepository)를 스프링빈에 등록하면된다.
- 이거만 하면 끝이다.
- 즉 설정파일에서 스프링 빈 등록을 다른 구현체, 즉 JdbcMemberRepository만 해주면 된다.
- 참고로 dataSource라는 녀석은 DI를 해줘야하긴함.

```
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
```

- 생성자 주입했고 spring이 datasource커넥션을 바탕으로 스프링 빈으로 등록을 해놔서 DI가 되는것.. ㅋ 이건 spring이 하는거다.(@Service같은 스프링빈 등록 과정이 없어도 스프링이 알아서 스프링빈 등록)

- 즉 중요한건 어플 실행되는 다른 코드를 수정한거 없이 설정파일(어셈블리)만 수정하면 된다는게 중요하다.
- 이는 OCP (개방 폐쇄정책)을 지켰다 할수 있다. 확장은 가능하게, 변경 수정은 불가능하게..
- 이렇게 스프링 컨테이너에서 제공하는 DI를 이용하면 **기존 코드는 하나~~~~~~~도 손안대고 설정파일만 변경해서 구현 클래스를 바꿀수 있다.**
- 이젠 DB를 통해서 회원이 등록되고 조회가 잘된다.(서버 끊겨도 DB에 회원 정보 잘 저장된다..)

# 알게된점

- 많은 이유가 있겠지만 Spring을 이용하는 이유를 하나알게됨.
- spring컨테이너에서 등록되어진 스프링빈을 변경만하고 새로운 스프링빈을 DI하면 그걸로 확장은 OK, 다른코드들 수정할필요가없음, 즉, 스프링 빈 등록하는 부분(설정파일, @Configuration) 파일만 변경하고 그외의 어플관련 다른 코드는 건들지않아도된다. 이게 Spring의 장점이자 사용하는 이유.
- spring의 장점은 이러한 다형성이다. 흠. 다형성이 뭔지 아직 잘모르지만 뭔가 변경이있을때 설정파일만을 변경하고 기존 코드는 하나~도 변경하지 않아도 새로운 Dependency를 Injection할수 있다는 게 큰장점! (스프링 컨테이너에서 제공하는 기능), spring사용안하면 구현클래스 변경되면 해당 클래스 사용하는 클래스들 모두 코드를 변경해야함.
- 다형성은 interface를 두고 구현체를 바꿔끼기 가능하다. 이런걸 다형성이라고한다.
- 스프링은 이걸 굉장히 편리하게되도록 스프링 컨테이너가 지원해준다.(Dependency Injection을 통해서)
