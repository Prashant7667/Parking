package org.example.Service;

import org.example.Models.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User userSignupService(User user){
        User findUser= userRepository.findById(user.getId());
        return userRepository.save(user);
    }
}
