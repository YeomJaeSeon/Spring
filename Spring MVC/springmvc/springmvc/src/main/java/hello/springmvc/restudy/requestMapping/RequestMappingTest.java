package hello.springmvc.restudy.requestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class RequestMappingTest {

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
        return "findUser : " + userId;
    }

    @PatchMapping("/{userId}")
    public String changeUser(@PathVariable String userId){
        return "change user userId : " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete user Id : " + userId;
    }

}
