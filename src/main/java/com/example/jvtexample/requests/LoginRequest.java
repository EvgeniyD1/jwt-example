package com.example.jvtexample.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "Login request with login and password")
public class LoginRequest implements Serializable {

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "write down your username")
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "create your password")
    private String password;
}
