package com.pangsoramdepo.rbac.controller;

import com.pangsoramdepo.rbac.dto.AuthenticationDto;
import com.pangsoramdepo.rbac.response.AuthenticationResponse;
import com.pangsoramdepo.rbac.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDto authenticationDto) throws Exception {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getUsername(),
                        authenticationDto.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok("Incorrect Username or Password");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authenticationDto.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
