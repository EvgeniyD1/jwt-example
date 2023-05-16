package com.example.jvtexample.controller;

import com.example.jvtexample.config.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<?> helloAuthorizedUser(@RequestHeader("Authorization") String tokenHeader){
        String token = tokenHeader.substring("Bearer ".length());
        if (jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            return ResponseEntity.ok("Hello, " + username + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> helloAuthorizedAdmin(@RequestHeader("Authorization") String tokenHeader){
        String token = tokenHeader.substring("Bearer ".length());
        if (jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            return ResponseEntity.ok("Hello, " + username + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/user2")
    public String helloAuthorizedUser2(){
        return "HELLO USER";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin2")
    public String helloAuthorizedAdmin2(){
        return "HELLO ADMIN";
    }
}
