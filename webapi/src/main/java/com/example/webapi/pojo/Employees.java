package com.example.webapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employees {
    @NotNull
    private Integer id;
    @NotEmpty
    private String username;
    @JsonIgnore
    private String password;
    @NotEmpty
    private String empName;
    private Integer sex;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private String deptName;
    @NotEmpty
    private String empDegreeName;
}
