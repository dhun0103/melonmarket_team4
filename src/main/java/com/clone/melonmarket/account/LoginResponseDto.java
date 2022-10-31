package com.clone.melonmarket.account;

import com.clone.melonmarket.customUtil.Chrono;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private String accountName;

    private String message;

    private int statusCode;

    public LoginResponseDto(String accountName, String message, int statusCode) {
        this.accountName = accountName;
        this.message = message;
        this.statusCode = statusCode;
    }
}
