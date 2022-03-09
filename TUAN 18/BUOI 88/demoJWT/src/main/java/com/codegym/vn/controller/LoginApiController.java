package com.codegym.vn.controller;

import com.codegym.vn.model.AppUser;
import com.codegym.vn.service.AppUserService;
import com.codegym.vn.service.IAppUserService;
import com.codegym.vn.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class LoginApiController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser) {
        //lay user va pasword de xac thuc
        //Tao ra token

        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateTokenLogin(authentication);
            return token;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/register")
    public void register(@RequestBody AppUser appUser) {
        String password = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(password);
        appUserService.save(appUser);
    }
}
