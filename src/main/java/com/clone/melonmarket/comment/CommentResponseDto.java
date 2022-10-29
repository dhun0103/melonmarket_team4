package com.clone.melonmarket.comment;

import com.clone.melonmarket.customUtil.Chrono;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private String accountName;
    private String comment;
    private String createdAt;


    public CommentResponseDto(Comment comment){
        this.postId=comment.getPost().getPostId();
        this.commentId=comment.getCommentId();
        this.accountName=comment.getAccount().getAccountName();
        this.comment=comment.getComment();
        this.createdAt = Chrono.timesAgo(comment.getCreatedAt());
    }
}
