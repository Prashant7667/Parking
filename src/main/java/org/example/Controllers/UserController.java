package org.example.Controllers;
import org.example.Models.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<User> signUp(@RequestBody User user) {
       User savedUser =  userService.userSignupService(user);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(savedUser);
    }
}
