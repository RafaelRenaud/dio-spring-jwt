package dio.dio.spring.jwt.controller;

import dio.dio.spring.jwt.model.entity.User;
import dio.dio.spring.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }
}
