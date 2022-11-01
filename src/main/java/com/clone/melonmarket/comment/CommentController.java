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



    // 댓글 작성
    @PostMapping("{postId}/comment")
    public GlobalResponseDto createComment(@PathVariable Long postId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getAccount());
    }


    //대댓글 작성
//    @PostMapping("{commentId}/reply")
//    public GlobalResponseDto createReply(@PathVariable Long commentId,
//                                         @RequestBody CommentRequestDto commentRequestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return commentService.createReply(commentId, commentRequestDto, userDetails.getAccount());
//    }

    @DeleteMapping ("/comment/{commentId}")
    public GlobalResponseDto deleteComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getAccount());
    }
}
