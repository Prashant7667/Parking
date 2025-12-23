package org.example.Service;
import org.example.Models.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser=userRepository.findByEmail(email).orElse(null);
        if(findUser != null) {
            throw new UsernameNotFoundException("User is Not present");
        }
        return new UserDetailsImpl(findUser.getEmail(),findUser.getPassword(),"User");
    }
}
