package com.clone.melonmarket.post;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String price;
    @NotBlank
    private String place;


}
