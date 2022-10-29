package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String contents;
    private String price;
    private Long postLikeCount;
    private String place;


    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany
    private List<Image> image;

    public Post(PostRequestDto postRequestDto, Account account) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.price = postRequestDto.getPrice();
        this.place = postRequestDto.getPlace();
        this.account = account;
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.price = postRequestDto.getPrice();
        this.place = postRequestDto.getPlace();
    }

}
