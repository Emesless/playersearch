package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
