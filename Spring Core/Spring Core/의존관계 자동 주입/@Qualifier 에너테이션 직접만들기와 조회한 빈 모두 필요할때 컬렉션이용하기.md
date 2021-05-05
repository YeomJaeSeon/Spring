# @Qualifier

- 의존관계 주입할때 선택된 (조회된)빈이 두개이상이면 `@Qualifier`로 빈 이름 지정할수있다 배웠따.
- 그런데 식별할 이름이 오타가 나도 String으로 입력되므로 뭐 에러를 찾기힘듬.
- 즉 버그찾기가어렵다.
- 그래서 에너테이션을 직접 만들어서 해당 에너텡션을 `@Qualifier`로 사용하면 컴파일시 에러를 찾기가 좋다. -> 버그를 줄이기좋다!

> 버그 줄이기 위해 런타임에러 -> 컴파일에러로 가져오는 방법이다.

# 스프링의 조회된 빈 다가져오는 방법

- 저번에 등록된 스프링빈 다 가져오는 방법을 한번 실습해봤따. (그리고 중복되는 빈은 NoUniqueBean..예외터진다.)
- 이번엔 해당 타입으로 조회된 빈을 모두 가져오는 방법을 알아보자.
- Map, List컬렉션을 이용한다.
- 해당 컬렉션의 지네릭 타입에 원하는 타입을 지정하면 해당 타입의 조회된 빈들이 컬렉션에 add가 된다. 이는 자동으로 add가되는데 `Spring의 특수 기능`이다.

```
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
```

- 이런식으로 하면 Map, List에 `DiscountPolicy`의 타입의 조회된 빈들이 쏙쏙 자동으로들어간다. 참고로 Map의 key인 String에는 스프링 빈 이름이들어간다.(그 클래스 첫글자 소문자인녀석.)
- 이를 이용해 간편하게 컬렉션에 들어간 스프링 빈을 이용할수있다.

# 결론

- 조회된 타입이 둘이상이면 NoSuchBeanException 예외가 터졌다.
- 그래서 우린 `@Primary`, `@Qualifier`등으로 구분하는 방법을 배웠다.
- 그리고 조회된 스프링 빈을 컬렉션에 넣는 스프링의 특수기능도 배웠다.
- 컬렉션 클래스로 조회된 스프링빈을 담으면 유용하게 사용할수있는 경우가 꼭있으니 꼭 기억하자.
