package ru.emesless.searchplayers.DTO;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
