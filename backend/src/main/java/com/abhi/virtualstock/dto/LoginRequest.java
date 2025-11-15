package com.abhi.virtualstock.dto;

import lombok.Getter;

@SuppressWarnings(value = "unused")
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
