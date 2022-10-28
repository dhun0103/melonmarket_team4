package com.clone.melonmarket.account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AccountNameRequestDto {

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String accountName;

}
