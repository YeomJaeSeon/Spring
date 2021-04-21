package food.studentsBestt.study.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class MemberController {

    @GetMapping
    public String newForm(){
        return "study/new-form"; // viewResolver에의해서 thymeleaf view rendering
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Member member, Model model){
        System.out.println(member);
        model.addAttribute(member);
        System.out.println("MemberController.save");
        return "study/save";
    }
}
