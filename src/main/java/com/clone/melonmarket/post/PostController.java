package com.clone.melonmarket.post;


import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    @PostMapping(value = "/posts", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public GlobalResponseDto createPost(@RequestParam("post") String post,
                                        @RequestPart("image") List<MultipartFile> image,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        Gson gson = new Gson();
        PostRequestDto postRequestDto = gson.fromJson(post,PostRequestDto.class);

        return postService.createPost(image, postRequestDto, userDetails);
    }
}
