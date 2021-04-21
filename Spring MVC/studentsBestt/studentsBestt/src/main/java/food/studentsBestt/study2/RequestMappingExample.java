package food.studentsBestt.study2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("/users")
public class RequestMappingExample {

    @GetMapping
    public String users(){
        log.info("show Users");
        return "ok";
    }

    @PostMapping
    public String createUser(){
        log.info("create User");
        return "ok";
    }

    @GetMapping("/{userId}")
    public String user(@PathVariable String userId){
        log.info("findUserId={}", userId);
        return "ok";
    }

    @PatchMapping("/{userId}")
    public String editUser(@PathVariable String userId){
        log.info("editUserId={}", userId);
        return "ok";
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        log.info("deleteUserId={}", userId);
        return "ok";
    }
}
