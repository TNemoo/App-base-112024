package com.svl.servicebase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PhoneNumberDto {

    @NotBlank(message = "you have to full this field")
    @Pattern(regexp = "\\++\\d{2,}+\\-+\\d{3}+\\-+\\d{3}+\\-+\\d{2}+\\-+\\d{2}",
            message = "Format phone number: +XX-XXX-XXX-XX-XX") // 17 symbols
    private String phoneNumber;

    private boolean priority = false;

    @Length(max = 100, message = "max number of symbols is 100")
    private String description;
}