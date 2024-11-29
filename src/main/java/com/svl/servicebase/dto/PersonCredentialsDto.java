package com.svl.servicebase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PersonCredentialsDto {

    @NotBlank(message = "you have to full this field")
    @Pattern(regexp = "\\++\\d{2,}+\\-+\\d{3}+\\-+\\d{3}+\\-+\\d{2}+\\-+\\d{2}",
            message = "Format phone number: +XX-XXX-XXX-XX-XX") // 17 symbols
    private String login;

    @NotBlank(message = "you have to full this field")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$", message = "Password must be exactly 6 characters long and contain only letters and digits.")
    private String password;

}
