package com.clone.melonmarket.account;

import com.clone.melonmarket.global.TimeStamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column
    private String email;

    @Column
    private String accountName;

    @Column
    private String accountPw;

    @Column
    private String phoneNum;

    @Column
    private double temp = 36.5;



    public Account(AccountRequestDto accountRequestDto) {
        this.email = accountRequestDto.getEmail();
        this.accountName = accountRequestDto.getAccountName();
        this.accountPw = accountRequestDto.getAccountPw();
        this.phoneNum = accountRequestDto.getPhoneNum();

    }

}
