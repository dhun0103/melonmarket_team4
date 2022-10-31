package com.clone.melonmarket.comment;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.cocomment.Cocomment;
import com.clone.melonmarket.global.TimeStamped;
import com.clone.melonmarket.post.Post;
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
public class Comment extends TimeStamped{
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

    @Column
    private Long step;

//    @Column
//    @GeneratedValue
//    private Long parentNum;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Cocomment> cocomment;

    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
        this.comment = commentRequestDto.getComment();
        this.post = post;
        this.account = account;
    }
    //댓글 대댓글 한번에 구현

//    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
//        this.comment = commentRequestDto.getComment();
//        this.post = post;
//        this.account = account;
//        this.step = commentRequestDto.getStep();
//        this.parentNum = commentRequestDto.getStep() == 1 ? null : commentRequestDto.getId();
//    }

}
