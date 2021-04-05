# 실제 개발에선 요청파라미터를 받으면 객체로 변환한다. 이를 자동화해주는 스프링에서 제공하는 @ModelAttribute를 알아보자.

- `@ModelAttribute`를 사용하지않아도 그냥 뭐 객체 set~ set~으로 요청 파라미터를객체로 직접 변환해도된다.

- 그러나 스프링이 제공하는 `@ModelAttribute`를 사용하면 완전 편하게 객체로 변환해주는 작업을 자동화할수있다.

- 코드로 보면 어떤식으로 변하냐면

`@ModelAttribute 사용전 - 파라미터 직접 객체변환`

```
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @RequestParam String username,
            @RequestParam int age
    ){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("data ={}", helloData);
        log.info("username ={}, age = {}", helloData.getUsername(), helloData.getAge());


        return "ok";
    }
```

`@ModelAttribute사용을 통한 요청파라미터 객체변환 자동화!`

```
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        log.info("data ={}", helloData);
        log.info("username ={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
```

- 그냥 딱봐도 완전 사기다. 너무편하다.
- `@ModelAttribute`가 있으면 HelloData객체를 생성하고 요청 파라미터의 이름의프로퍼티를 찾고 프러퍼티의 setter메서드를 통해서 값을 저장한다.(바인딩이라고한다.)
- 이러한 동작을 `@ModelAttribute`는 자동으로 해준다.

## 프로퍼티는? 먼데?

- setXxx, getXxx 에서 xxx가 프로퍼티이다. 프로터피의 값을 변경하면 setXxx가 호출되고 조회하면 getXxx가 호출된다.
