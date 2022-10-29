package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myPageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    public MyPage(Account account){
        this.account=account;
    }

}
