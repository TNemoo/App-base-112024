package com.svl.servicebase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDto {

    @NotBlank
    @Pattern(regexp = "^[A-ZА-я][a-zа-я]{0,49}$",
            message = "Format name: 'Petr' or 'Петр', max 20 symbols")
    private String firstname;

    @NotBlank
    @Pattern(regexp = "^[A-ZА-я][a-zа-я]{0,49}$",
            message = "Format name: 'Petrovich' or 'Петрович', max 20 symbols")
    private String middleName;

    @NotBlank
    @Pattern(regexp = "^[A-ZА-я][a-zа-я]{0,49}$",
            message = "Format name: 'Petrov' or 'Петров', max 40 symbols")
    private String surname;

    private List<PhoneNumberDto> phoneNumbers = new ArrayList<>();

    private List<EmailDto> email = new ArrayList<>();
}
