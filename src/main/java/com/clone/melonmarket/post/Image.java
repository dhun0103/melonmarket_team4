package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String image;

    @ManyToOne
    @JoinColumn(name = "account")
    private Post post;



    public Image(String image, Post post) {
        this.image = image;
        this.post = post;
    }

}
