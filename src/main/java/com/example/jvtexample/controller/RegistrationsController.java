package com.example.jvtexample.controller;

import com.example.jvtexample.config.JwtTokenUtil;
import com.example.jvtexample.requests.LoginRequest;
import com.example.jvtexample.requests.RegistrationRequest;
import com.example.jvtexample.response.JwtResponse;
import com.example.jvtexample.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class RegistrationsController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "hello controller", description = "Hello Hello", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @GetMapping("/hello")
    public String hello(){
        return "HELLO";
    }

    @Operation(summary = "registration controller", description = "write your details here", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody RegistrationRequest request){
        if (userService.findByUsername(request.getUsername())!=null){
            return ResponseEntity.badRequest().body("A user with the same name already exists!");
        }
        userService.createNewUser(request);
        return ResponseEntity.ok("Successful registration!");
    }

    @Operation(summary = "login controller", description = "write your details here, and receive jwt token", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        у меня одна роль
        String role = userDetails.getAuthorities().stream().findFirst().orElseThrow().getAuthority();

        return ResponseEntity.ok(new JwtResponse(userDetails.getUsername(), role, token));
    }
}
