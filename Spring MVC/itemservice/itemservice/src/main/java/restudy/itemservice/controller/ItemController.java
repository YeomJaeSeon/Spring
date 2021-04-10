package restudy.itemservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import restudy.itemservice.domain.Item;
import restudy.itemservice.domain.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        log.info("items={}", items);
        return "basic/items";
    }

    @PostConstruct
    public void construct(){
        itemRepository.save(new Item("파브르곤충기", 10000, 10));
        itemRepository.save(new Item("삼국지",20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품등록 GET과 POST는 요청 URL이같지만 요청 메서드가다르다. 이러면 URL너무간단하게 설계가능
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String add(Item item, RedirectAttributes redirectAttributes){ // 타입이 클래스면 @ModelAttribute생략가능
        if(item.getItemName() == "" || item.getItemName() == null || item.getPrice() == null || item.getQuantity() == null){
           redirectAttributes.addAttribute("status", false);
            return "redirect:/basic/items/add";
        }
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId()); // pathvariable에 치환됨 . 알아서.
        redirectAttributes.addAttribute("status", true); // 이건 쿼리파라미터에추가됨
        return "redirect:/basic/items/{itemId}"; // 리다이렉트 - PRG를 위해서, POST가 최신이면 브라우저에서 새로고침하면 또 POST요청됨.
    }

    @GetMapping("/{itemId}/edit")
    public String EditForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(Item item, @PathVariable Long itemId, RedirectAttributes redirectAttributes){
        if(item.getItemName() == "" || item.getItemName() == null || item.getPrice() == null || item.getQuantity() == null){
            redirectAttributes.addAttribute("status", false);
            return "redirect:/basic/items/{itemId}/edit";
        }
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId, RedirectAttributes redirectAttributes){
        itemRepository.delete(itemId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items";
    }

}
