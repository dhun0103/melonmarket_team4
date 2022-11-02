package com.clone.melonmarket.post;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class PostUpdateReqeuestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String price;
    @NotBlank
    private String place;
    private Long imageId;
}
