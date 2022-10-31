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
    private List<Image> images;
    private Boolean status;

    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtos,
                           List<Image> images) {
        this.postId = post.getPostId();
        this.accountName = post.getAccount().getAccountName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = commentResponseDtos;
        this.postLikeCount = post.getPostLikeCount();
        this.createdAt = Chrono.timesAgo(post.getCreatedAt());
        this.modifiedAt = Chrono.timesAgo(post.getModifiedAt());
        this.images = images;
        this.price = post.getPrice();
        this.place = post.getPlace();
        this.status = post.getStatus();
    }
}
