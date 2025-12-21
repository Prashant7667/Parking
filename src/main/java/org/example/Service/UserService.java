package org.example.Service;

import org.example.Models.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User userSignupService(User user){
        Optional<User> findUser= userRepository.findById(user.getId());
        if(!findUser.isPresent()){
            throw new IllegalStateException("User is already There");
        }
        return userRepository.save(user);
    }
}
