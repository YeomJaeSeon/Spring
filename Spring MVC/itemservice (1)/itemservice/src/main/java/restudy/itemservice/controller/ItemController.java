package restudy.itemservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import restudy.itemservice.domain.Item;
import restudy.itemservice.domain.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
public class ItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @PostConstruct
    public void test(){
        itemRepository.save(new Item("test1", 1000, 1));
        itemRepository.save(new Item("test2", 2000, 2));
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,Item item, RedirectAttributes redirectAttributes){
        itemRepository.update(itemId, item);
        redirectAttributes.addAttribute("status", "edit");

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    @PostMapping("/add")
    public String add(Item item, RedirectAttributes redirectAttributes){
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", "save");
        return "redirect:/basic/items/{itemId}";
    }

    // 삭제를 viewname을반환해서 뷰템플릿내려주려햇는데
    // URL이 변경이안되므로 그냥 redirect로 basic/items로 내려줌
    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId, RedirectAttributes redirectAttributes){
        itemRepository.delete(itemId);
        redirectAttributes.addAttribute("status", "delete");

        return "redirect:/basic/items";
    }
}
