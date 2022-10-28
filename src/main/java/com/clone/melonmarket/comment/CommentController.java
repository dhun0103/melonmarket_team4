package com.clone.melonmarket.comment;

import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("{postId}/comment")
    public GlobalResponseDto createComment(@PathVariable Long postId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getAccount());
    }

    @PutMapping("/comment/{commentId}")
    public GlobalResponseDto deleteComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getAccount());
    }
}
