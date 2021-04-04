# 전까지 한것들

- 컨트롤러(클래스)하나로 통합했다. 그런데 `@RequestMapping`메서드에서 ModelAndView를 항상 반환한다. 좀더 실용적으로 바꿔보자. 우리가 MVC 프레임워크 만들때 V3에서 V4로 업그레이드한 과정을 이용해서.
- `@RequestMapping`은 매핑되는 URL이오면 메서드를 호출한다. 그러나 HTTP 메서드는 매핑이안됨. 그래서 `@GetMapping`이라던가 `@PostMapping`이라는게 있음요!

# 이러한것들을 좀더 실용적으로 바꿔보자.

- 각 컨트롤러들이 ModelAndView를 만들어서 반환하지말고 그냥 논리적인 viewName만 반환하도록해보자 . String으로.
  애너테이션 기반의 컨트롤러는 이렇게 String으로 리턴해도 알아서 viewName으로 인식한다. 그래서 전처럼 ModelAndView를 반환해도된고 아니면 vieWName만 String으로 반환해도됨

이전코드

```
    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }
```

변경코드

```
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }
```

- 띠용 그런데 `@GetMapping`은 처음봐 뭐지?
- `@ReqeustMapping`에는 요청된 URL과 메서들를 매핑해준다.그러나 HTTP 메서드는 매핑할수가없다. 그래서 GET으로 요청이들어오던 POST로 들어오던 모두다 이 메서드가 실행된다.
- 요청온 HTTP메서드 구분하게 도와준다. `@GetMapping`은 요청온 HTTP 메서드가 GET!

- 변경한코드는 더이상 ModelAndVeiw를 반환하지 않고 String viewName을 반환.

- 참고로 애너테이션기반 컨트롤러를 사용하면 이제 HttpServletRequest객체를 통해서 요청 파라미터 데이터를 사용안해도 `@RequestParam`을 이용해서 요청파라미터 데이터 꺼낼수있다.

```
 @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
                             ){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }
```

- 요청온 메서드가 POST이고 URL이 `/save`이면 이 save()메서드가 호출되는데 save의 매개변수에 보면 `@RequestParam`이있다. 이 애너테이션을 통해서 요청파라미터 데이터 꺼낼수있따.
- 위 컨트롤러 메서드도 이제 ModelAndView를 반환하지않고 String - viewNAme만 반환한다.
- 그럼 어케 모델에 데이터담아요 ? -> 매개변수로 Model을 받는다. 여기에 그냥 넣으면된다.(이전에 했던 V3 -> V4 실용적인 컨트롤러로 업그레이드와 같은 방식임.)
- `@GetMapping`안을 보면 `@RequestMapping`을 볼수있다. 이렇게 스프링은 애너테이션을 조합해서 계쏙 업그레이드하며 개발자가 사용하기 쉽게 만들어옴.
