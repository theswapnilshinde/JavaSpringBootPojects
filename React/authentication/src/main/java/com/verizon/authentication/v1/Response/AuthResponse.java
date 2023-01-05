package com.verizon.authentication.v1.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private String username;
    private String role;
    private String token;
    private String result;
    private String status;

    public AuthResponse(String username, String role){
        this.role = role;
        this.username = username;
    }
}
