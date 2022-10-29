package com.clone.melonmarket.cocomment;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cocomment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocommentId;

    @Column
    private String cocomment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    public Cocomment(CocommentRequestDto cocommentRequestDto, Comment comment, Account account) {
        this.cocomment = cocommentRequestDto.getCocomment();
        this.comment = comment;
        this.account = account;
    }


}
