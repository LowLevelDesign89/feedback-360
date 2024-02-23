package com.app.f360.models.entity;

import com.app.f360.models.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document("users")
@Data
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String managerId;
    private Set<Role> roles;
    private List<String> reportees;
    private boolean isActive;
    private String password;
}
