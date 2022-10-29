package com.clone.melonmarket.post;

import com.clone.melonmarket.comment.CommentResponseDto;
import com.clone.melonmarket.customUtil.Chrono;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String accountName;
    private String title;
    private String content;
    private String price;
    private String place;
    private List<CommentResponseDto> comments;
    private Long postLikeCount;
    private String createdAt;
    private String modifiedAt;
    private List<Image> image;

    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtos) {
        this.postId = post.getPostId();
        this.accountName = post.getAccount().getAccountName();
        this.title = post.getTitle();
        this.content = post.getContents();

        this.comments = commentResponseDtos;
        this.postLikeCount = post.getPostLikeCount();
        this.createdAt = Chrono.timesAgo(post.getCreatedAt());
        this.modifiedAt = Chrono.timesAgo(post.getModifiedAt());

    }
}
