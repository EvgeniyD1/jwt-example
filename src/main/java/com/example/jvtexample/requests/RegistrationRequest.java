package com.example.jvtexample.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "Registration request with login, password and role")
public class RegistrationRequest implements Serializable {

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "write down your username")
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "create your password")
    private String password;

//  мне влом что-то придумывать
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "choose your role")
    private String role;
}
