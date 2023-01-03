package com.verizon.authentication.v1.Request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
}
