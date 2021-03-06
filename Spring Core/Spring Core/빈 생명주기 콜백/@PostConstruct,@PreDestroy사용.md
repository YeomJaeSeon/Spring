# 이 애너테이션들을 사용해서 초기화콜백, 소멸전 콜백 이벤트를 이용하자.

- `@PostConstruct`는 이름 그대로 생성 이후 즉, 의존관계 주입 끝난 이후라는 뜻으로 초기화 콜백 메서드 위에 이 애너테이션을 붙이면된다.

- `@PreDestroy`는 이름 그대로 소멸 전 이라는 뜻으로 빈 소멸전 인 소멸전 콜백으로 사용할 메서드 위에 이 애너테이션을 붙이면된다.

- 애너테이션 하나만붙이면되기에 너무나 간단하고
- 자동빈(컴포넌트 스캔)등록 방식과도 너무 잘어울린다.
- 그리고 스프링에 종속되어있는 에너테이션이아니다. import된 패키지명을 보면 `javax`로 자바 표준이다.
- 그러나 외부라이브러리를 초기화 종료는 하지못한다.(코드를 변경하는게 아닌 애너테이션만 딸랑 붙이는 것이므로) 이떈 `@Bean`의 initMethod, destroyMethod파라미터를 이용하자.

# 결론

- 의존관계 주입 끝난 초기화 콜백 메서드 적용할땐 `@PostConstruct`, 빈 소멸되기 직전 소멸전 콜백 메서드 적용할땐 `@PreDestroy`애너테이션을 사용하자.
- 외부라이브러리에 대해서 초기화콜백, 소멸전 콜백 이벤트를 등록하고 싶을땐 `@Bean(initMethod="~", destroyMethod="~")`를 사용하자.
