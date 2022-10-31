package com.clone.melonmarket.post;


import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;


    @PostMapping("/api/{postId}/like")
    public GlobalResponseDto createPostLike(@PathVariable Long postId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postLikeService.createPostLike(postId, userDetails);
    }
}
