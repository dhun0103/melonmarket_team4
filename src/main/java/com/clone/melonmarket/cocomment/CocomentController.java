package com.clone.melonmarket.cocomment;

import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CocomentController {

    private final CocommentService cocommentService;

    @PostMapping("/{commentId}/cocomment")
    public GlobalResponseDto createCocomment(@PathVariable Long commentId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody CocommentRequestDto cocommentRequestDto) {
        return cocommentService.createCocomment(commentId, userDetails.getAccount(), cocommentRequestDto);
    }

    @DeleteMapping("/cocomment/{cocommentId}")
    public GlobalResponseDto deleteCocomment(@PathVariable Long cocommentId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cocommentService.deleteCocomment(cocommentId, userDetails.getAccount());
    }
}
