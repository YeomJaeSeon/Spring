package food.studentsBestt.food.controller;

import food.studentsBestt.food.domain.Food;
import food.studentsBestt.food.domain.FoodRepository;
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
@RequestMapping("/school/foods")
public class FoodController {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public String foods(Model model){
        List foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "school/foods";
    }

    @PostConstruct
    public void createFoods(){
        foodRepository.save(new Food("햄버거"));
        foodRepository.save(new Food("피자"));
    }

    @GetMapping("/{foodId}/like")
    public String like(@PathVariable Long foodId){
        foodRepository.like(foodId);

        return "redirect:/school/foods";
    }

    @GetMapping("/{foodId}/hate")
    public String hate(@PathVariable Long foodId){
        foodRepository.hate(foodId);

        return "redirect:/school/foods";
    }

    @GetMapping("/add")
    public String addForm(){
        return "school/addForm";
    }

    @PostMapping("/add")
    public String add(@RequestParam String foodName){
        Food food = new Food(foodName);
        foodRepository.save(food);
        return "redirect:/school/foods";
    }

    // 음식상세
    @GetMapping("/{foodId}")
    public String food(@PathVariable Long foodId, Model model){
        Food food = foodRepository.findById(foodId);
        model.addAttribute("food", food);

        return "school/food";
    }

    //음식 삭제
    @GetMapping("/{foodId}/delete")
    public String delete(@PathVariable Long foodId){
        foodRepository.delete(foodId);

        return "redirect:/school/foods";
    }

    //음식 랭킹
    @GetMapping("/ranking")
    public String ranking(){
        return "school/ranking";
    }
}
