package com.example.webapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employees extends BasePojo {
    @NotEmpty
    private String empName;
    private Integer sex;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private Integer deptName;
    @NotEmpty
    private Integer empDegreeName;
}
