package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
//@RequiredArgsConstructor 이거쓰면 final이 붙은 애를 가지고 생성자를 만들어줌. - 생성자주입
public class BasicItemController {
    private final ItemRepository itemRepository;

    // 생성자 주입.
    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item.html";
    }

    // 이전에 내가 스스로한부분 영한님이랑 차이를 보자.
//    @PostMapping("/{itemId}")
//    public String itemsAddItem(@ModelAttribute Item item){
//        // 아이템 이름, 수량, 가격 안들어오면 에러.
//        if(item.getItemName() == "" || item.getQuantity() == null || item.getPrice() == null){
//            log.info("itemName= {} , itemQuentity = {}, itemPrice = {}", item.getItemName(), item.getQuantity(), item.getPrice());
//            return "redirect:/basic/items/add";
//        }
//        Item savedItem = itemRepository.save(item);
//        Long id = savedItem.getId();
//        return "redirect:/basic/items/" + id; // 나는 상품등록하면 상품 상세로 가는 내부호출을 redirect로함.
//    }

//    // 상품 등록 폼 gogo
//    @GetMapping("/add")
//    public String addForm(Model model){
//        int size = itemRepository.findAll().size();
//        model.addAttribute("nextItemId", size + 1); // 다음 아이템 Id
//        return "basic/addForm";
//    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add") //위와 요청 URL이동일하나 여긴 POST메서드 매핑
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model
                       ){
        Item item = new Item(itemName, price, quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";

    }

//    @PostMapping("/add") //위와 요청 URL이동일하나 여긴 POST메서드 매핑
    public String addItemV2(
            @ModelAttribute("item") Item item
            // ModelAttribute는 먼저 모델 객체를 만들어주고 뷰에 모델에 넣어주는 역할까지수행함.
    ){
        itemRepository.save(item);
//        model.addAttribute("item", item); @ModelAttribute("item")덕분에 이 이름이 맞아야한다.item

        return "basic/item";
    }
//    @PostMapping("/add")
    public String addItemV3(
            @ModelAttribute Item item // ("item")도생략했다. -> 이러면 첫번째 클래스이름 소문자로바꿔서그걸 모델에 담는다.
    ){
        // mode.addAttribute("item", item);이 동작된다. 알아서.
        itemRepository.save(item);
        return "basic/item";
    }

    // basic/item의 뷰템플릿을 웹브라우저로 내려줬따.
    // 웹브라우저에서 새로고침을 하면? 웹브라우저는 이전에 했던 행동을 그대로 서버에게요청한다.
    // POST /add 와 item데이터를 전달하고 다시 이 컨트롤러가 받아서 아이템을 저장하고 다시 뷰템플릿을내려준다.
//    @PostMapping("/add")
    public String addItemV4(Item item){ // @ModelAttribute자체도 생략할수있음.
        itemRepository.save(item);
        return "basic/item";
    }

    // 웹브라우저에서 새로고침하면 POST로 요청하는것대신 서버에서 redicrect로 GET 페이지를 내려줘서
    // 웹브라우저의 최신 동작인 GET을 동작하게끔 변경해보자.
//    @PostMapping("/add")
    public String addItemV5(Item item){ // @ModelAttribute자체도 생략할수있음.
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){ // @ModelAttribute자체도 생략할수있음.
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); // 저장 되었다는 flag
        return "redirect:/basic/items/{itemId}"; // 여기 못들어가는애들은 쿼리파라미터로들어가게됨.
    }


    //상품 수정 page gogo
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    // 상품 수정 POST요청
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; // @PathVariable에서 받은 itemId를 그대로 쓸수잇음.
    }
}
