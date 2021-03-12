# MVC

- Model View Controller
- 예전에는 컨트롤러 뷰 안나뉘어져있어 뷰에서 모든걸 했다. (model 1방식)
- 요새는 MVC스타일로 많이 한다.
- view는 보여주는데 집중, Model과 컨트롤러는 비즈니스 로직과 관련이 있거나 내부적인걸 처리하는데 집중해야함.

(타임리프의 장점은 서버와의 연결이없어도 html껍데기를 볼수가 있다. )

## MVC동작과정

1. 웹브의 요청
2. 내장 톰캣서버가 스프링에게 요청왔다알려줌
3. 스프링은 컨트롤러를 **먼저**보고 매핑된 메소드가 있는지 확인
4. 매핑된 메소드가 있으므로 반환된 문자열에 해당되는 파일에게 model데이터 던져줌
5. 뷰 해결자인 스프링의 viewResolver는 받은 데이터로 HTML로변환하여 웹브에 던져준다.(정적 컨텐츠에선 정적컨텐츠를 바로 웹브에 던져줌, 템플릿 엔진은 변환된 HTML을 던져줌)

```
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);

        return "hello-template";
    }
```

이 컨트롤러 메소드는
@RequestParam을 통해서 쿼리 데이터로 입력받은 데이터를 model데이터로 설정해서 viewResolver에게 던져줄수 있다.

# 결론

- 템플릿 엔진(타임리프, view)을 통해서 MVC가 가능했음
- 컨트롤러를 설정 -> 해당 컨트롤러는 모델을 뷰로 전달. 해당 모델을 받는 뷰는 템플릿 언어(타임리프)
