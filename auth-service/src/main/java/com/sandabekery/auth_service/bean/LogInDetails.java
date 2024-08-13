package com.sandabekery.auth_service.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogInDetails {
    private String loginUrl;
    private String authUrl;
}
