# 웹브라우저의 새로고침.

- 웹브라우저는 새로고침하면 최근에 서버에게 요청했던 동작을 그대로 한다.

## 상황

1. 상품등록 페이지에서 POST로 상품을 요청 (상품데이터를 함꼐 요청함.)
2. 서버(컨트롤러)는 요청을 받고 그냥 뷰 템플릿만 내려준다. 즉 응답 바디에 HTML만 내려줌
3. 웹브라우저는 URL이런건 변경없고 단순히 응답 바디에 내려온 HTML이 렌더링되어있는상태임

-> 즉, 이때 고객은 상품을 하나 산 상태이다.

여기서부터! 4. 실수로 고객이 새로고침을 누르면??? 이전에 요청했던 POST를 그대로 똑같이 요청한다. (웹브라우저의 가장 최근 동작은 서버에게 POST로 요청하면서 아이템 데이터를 같이 전달..) 그러면 계속해서 고객은 새로고침만 누를뿐인데 물건이 사지는상황이다.

## 해결

- Redirect를 통해서 웹브라우저에서 최근의 요청을 GET으로 하게끔 바꿔서 새로고침눌러도 GET으로 새로운 페이지만 조회하게하여 어떠한 데이터를 서버로 보내는 동작을 막는 방법!!!

- 정확힌 PRG (POST / Redirect/ GET) - 클라이언트의 POST요청에 대해서 서버는 데이터 처리를하고 Redirect를 통해서 Location으로 다시 GET요청 보내라고 클라이언트에게 알려주고 클라이언트는 자동 리다이렉트로 해당 Location URL로 GET요청을하게되어 GET요청 결과로 페이지를 받는다. 이렇게되면 웹브라우저의 가장 최근동작은 GET으로 페이지를 조회하는 것뿐이다!!(데이터를 서버로 전달을 막는다.) 그래서 고객이실수로 새로고침을 눌러도 GET으로 해당 페이지만 조회받지 어떠한 데이터도 서버로 전달되는 것을 막을수있다.

## 그런데 Redirect시 return "redirect:/basic/items/" + item.getId(); 로하면 item.getId()까 Long인 정수라 망정이지 한글이였으면 깨졌따. 이럴 경우를 대비해서 인코딩도 해주고 여러 방식을 지원하는 RedirectAttributes를 알아보자.

# RedirectAttributes

- 상품을 저장하고 redirect로 상품 상세화면 GET 요청하도록 해서 상품상세화면이 랜더되어있는상태이다. 근데 이게 사용자입장에선 뭐 상품등록된지안된지 애매하다. 즉 사용성이 떨어진다. 그래서 바로 물품상세갔을때와의 차이점을 두기위해 상품을등록하고 리다이렉트로 GET으로 상품상세화면 응답할땐 `RedirectAttributes`를 이용해서 쿼리파라미터에 데이터를 넘겨서 사용자가 '아 내가 등록한 상품 잘 등록되었구나를 알게해보자.' 추가로 사용성도 높이고 URL인코딩도 알아서해주고 pathVariable, 쿼리파라미터도 쉽게사용을 도와주기때문에 `RedirectAttributes`를사용하자.

```
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){ // @ModelAttribute자체도 생략할수있음.
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); // 저장 되었다는 flag
        return "redirect:/basic/items/{itemId}"; // 여기 못들어가는애들은 쿼리파라미터로들어가게됨.
    }
```

- 보면 RedirectAttributes를 사용하니 리다이렉트 하는부분에 itemId에 치환이 가능해졌다. 추가로 URL인코딩도 자동으로해주고 RedirectAttributes에 넣은 status는 쿼리파라미터로 들어가게된다.

그러면 리다이렉트 결과는
`http://localhost:8080/basic/items/3?status=true`
가된다.

## 타임리프 param과 th:if

- 타임리프에선 `th:if`로 해당조건이 참이면 실행되게할수도있고 파라미터에 들어오는 데이터를 타임리프에서 바로받을수도있다.(뷰가 바로받는거임), 사실 컨트롤러에서 모델에 파라미터 데이터넣고 뷰에서 모델에서 꺼내는게 정석인데 파라미터는 하도많이 사용이되기 때문에 이런식도 가능하게끔했따.
