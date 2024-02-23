package com.app.f360.models.dto.request;

import lombok.Data;

@Data
public class UserRegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String manangerId;
}
