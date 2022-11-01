package com.clone.melonmarket.comment;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.exception.CustomException;
import com.clone.melonmarket.exception.ErrorCode;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.clone.melonmarket.post.Post;
import com.clone.melonmarket.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 작성
    @Transactional
    public GlobalResponseDto createComment(Long postId, CommentRequestDto commentRequestDto,  Account account) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundPost)
        );


        // 얘네 쓰면 됨.
//        Comment comment = new Comment(commentRequestDto, post, account);
//        commentRepository.save(comment);


        //이거 쓸필요도 없이
//        if(commentRequestDto.getStep().equals(1L)) {
//            Comment comment = new Comment(commentRequestDto, post, account);
//            commentRepository.save(comment);
//            comment.setParentNum(null);
//        } else {
//            Comment comment = new Comment(recommentRequestDto, post, account);
//            commentRepository.save(comment);
//            comment.setParentNum(recommentRequestDto.getCommentId());
//        }



        Comment comment = new Comment(commentRequestDto, post, account);
        commentRepository.save(comment);

        return new GlobalResponseDto("Success create comment", HttpStatus.OK.value());
    }


    //댓글삭제

    @Transactional
    public GlobalResponseDto deleteComment(Long commentId, Account account) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundComment)
        );

        if(comment.getAccount().getAccountId().equals(account.getAccountId())) {
            commentRepository.deleteById(commentId);
            return new GlobalResponseDto("Success delete comment",HttpStatus.OK.value());
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }
}
