package com.clone.melonmarket.comment;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.cocomment.Cocomment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Cocomment> cocomment;

    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
        this.comment = commentRequestDto.getComment();
        this.post = post;
        this.account = account;
    }

}
