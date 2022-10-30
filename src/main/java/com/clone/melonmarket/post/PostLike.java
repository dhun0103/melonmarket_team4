package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostLike {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public PostLike(Account account, Post post) {
        this.account = account;
        this.post = post;
    }

}
