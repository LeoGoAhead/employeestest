package com.example.webapi.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BasePojo {
    @NotNull
    private Integer id;
}
