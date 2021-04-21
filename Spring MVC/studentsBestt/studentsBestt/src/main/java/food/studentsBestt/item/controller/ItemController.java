package food.studentsBestt.item.controller;

import food.studentsBestt.item.domain.Item;
import food.studentsBestt.item.domain.ItemRepository;
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
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 목록페이지
    @GetMapping
    public String items(Model model){
        List items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @PostConstruct
    public void createItems(){
        itemRepository.save(new Item("itemA", 1000, 10));
        itemRepository.save(new Item("itemB", 2000, 20));
    }

    //상세페이지
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    //등록
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.save(item);
        Long id = saveItem.getId();
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("flag", true);
        return "redirect:/basic/items/{id}";
    }

    // 수정
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@ModelAttribute Item item, @PathVariable Long itemId){
        Long id = item.getId();
        itemRepository.update(id, item);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId){
        itemRepository.delete(itemId);
        return "redirect:/basic/items";
    }
}
