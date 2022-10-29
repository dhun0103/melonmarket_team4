package com.clone.melonmarket.cocomment;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.comment.Comment;
import com.clone.melonmarket.comment.CommentRepository;
import com.clone.melonmarket.exception.CustomException;
import com.clone.melonmarket.exception.ErrorCode;
import com.clone.melonmarket.global.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocommentService {
    private final CocommentRepository cocommentRepository;
    private final CommentRepository commentRepository;
    public GlobalResponseDto createCocomment(Long commentId, Account account, CocommentRequestDto cocommentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundComment)
        );
        Cocomment cocomment = new Cocomment(cocommentRequestDto, comment, account);
        cocommentRepository.save(cocomment);
        return new GlobalResponseDto("Success create cocomment", HttpStatus.OK.value());
    }

    public GlobalResponseDto deleteCocomment(Long cocommentId, Account account) {
        Cocomment cocomment = cocommentRepository.findById(cocommentId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundCocomment)
        );
        if(cocomment.getAccount().getAccountId().equals(account.getAccountId())) {
            cocommentRepository.deleteById(cocommentId);
            return new GlobalResponseDto("Success delete cocomment", HttpStatus.OK.value());
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }
}

