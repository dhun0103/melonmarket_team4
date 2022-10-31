package com.clone.melonmarket.post;


import com.clone.melonmarket.customUtil.Chrono;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostAllResponseDto {

    private Long postId;
    private String accoountName;
    private String title;
    private String content;
    private String price;
    private String place;
    private Long postLikeCount;
    private String createdAt;
    private String modifiedAt;
    private List<Image> images;
    private Boolean status;

    public PostAllResponseDto(Post post) {
        this.postId = post.getPostId();
        this.accoountName = post.getAccount().getAccountName();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.place = post.getPlace();
        this.postLikeCount = post.getPostLikeCount();
        this.createdAt = Chrono.timesAgo(post.getCreatedAt());
        this.modifiedAt = Chrono.timesAgo(post.getModifiedAt());
        this.images = post.getImage();
        this.status = post.getStatus();
    }
}
