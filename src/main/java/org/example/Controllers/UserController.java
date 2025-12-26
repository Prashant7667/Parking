package org.example.Controllers;
import org.example.Config.JWTUtils;
import org.example.Models.User;
import org.example.Service.UserDetailsImpl;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @PostMapping ("/register")
    public ResponseEntity<User> signUp(@RequestBody User user) {
       User savedUser =  userService.userSignupService(user);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(savedUser);
    }
    @PostMapping ("/login")
    public String login(@RequestBody User user){
        var authToken=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        Authentication authentication=authenticationManager.authenticate(authToken);
        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        String jwt= jwtUtils.generateToken(userDetails.getUsername(),userDetails.getRole());
        return "Bearer "+jwt;
    }



}
