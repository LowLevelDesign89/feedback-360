package com.app.f360.models.dto.request;

import lombok.Data;

@Data
public class UserManagerUpdateRequestDTO {
    private String userId;
    private String currentManagerId;

    private String futureManagerId;
}
