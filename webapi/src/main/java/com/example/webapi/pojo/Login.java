package com.example.webapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Login extends BasePojo {
    @NotEmpty
    private String username;
    @JsonIgnore
    private String password;
    private String fullName;
}
