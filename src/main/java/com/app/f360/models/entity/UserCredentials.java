package com.app.f360.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usercredentials")
@Data
public class UserCredentials {
    @Id
    private String userId;
    private String password;
}
