package com.example.jvtexample.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof UsernameNotFoundException) {
            // Обработка ошибки, связанной с отсутствующим пользователем
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            log.error("User not found error: {}", authException.getMessage());
        } else if (authException instanceof BadCredentialsException) {
            // Обработка ошибки неверных учетных данных
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            log.error("Invalid credentials error: {}", authException.getMessage());
        } else {
            // Обработка других ошибок аутентификации
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            log.error("Unauthorized error: {}", authException.getMessage());
        }
    }
}
