package com.clone.melonmarket.comment;

import com.clone.melonmarket.cocomment.Cocomment;
import com.clone.melonmarket.customUtil.Chrono;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private String accountName;
    private String comment;
    private String createdAt;
//    private List<Cocomment> cocomment;


    public CommentResponseDto(Comment comment){
        this.postId=comment.getPost().getPostId();
        this.commentId=comment.getCommentId();
        this.accountName=comment.getAccount().getAccountName();
        this.comment=comment.getComment();
//        this.cocomment=comment.getCocomment();
        this.createdAt = Chrono.timesAgo(comment.getCreatedAt());
    }
}
