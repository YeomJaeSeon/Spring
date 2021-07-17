package web.mvc.mapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String users(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get UserID=" + userId;
    }

    @PatchMapping("/{userId}")
    public String changeUser(@PathVariable String userId){
        return "patch userId = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userID = " + userId;
    }
}
