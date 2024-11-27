package com.svl.servicebase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmailDto {

    @NotBlank(message = "you have to full this field")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
            message = "Format e-mail address: example@example.com")
    private String email;

    private boolean priority = false;

}
