package org.example.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException{
        String authHeader=request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=authHeader.substring(7);
        try{
            String email=jwtUtils.validateTokenAndGetEmail(token);
            var userDetails=userService.loadUserByUsername(email);
            var authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request,response);
    }
}
