package com.clone.melonmarket.account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class EmailRequestDto {

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

}
