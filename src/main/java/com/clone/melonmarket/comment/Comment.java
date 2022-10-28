package com.clone.melonmarket.comment;

import com.clone.melonmarket.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String comment;

    @JoinColumn(nullable = false)
    @JsonIgnore
    @ManyToOne
    private Post post;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Account account;

    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
        this.comment = commentRequestDto.getComment();
        this.post = post;
        this.account = account;
    }

}
