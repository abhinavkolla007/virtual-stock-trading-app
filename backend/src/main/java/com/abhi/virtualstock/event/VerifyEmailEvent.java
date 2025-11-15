package com.abhi.virtualstock.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;

@AllArgsConstructor
@Getter
public class VerifyEmailEvent {
    private final SimpleMailMessage simpleMailMessage;
}
