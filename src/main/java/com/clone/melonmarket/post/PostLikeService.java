package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.exception.CustomException;
import com.clone.melonmarket.exception.ErrorCode;
import com.clone.melonmarket.global.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public GlobalResponseDto createPostLike(Long postId, UserDetailsImpl userDetails) {

        // 해당 게시글 정보 가져오기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NotFoundPost));

        // 유저 정보 가져오기.
        Account account = userDetails.getAccount();


        if (!postLikeRepository.existsByPostAndAccount(post, account)) {
            post.updatePostLikeCnt(post.getPostLikeCount() + 1);

            PostLike postLike = new PostLike(account, post);
            postLikeRepository.save(postLike);
            return new GlobalResponseDto("게시글 좋아요 완료", HttpStatus.OK.value());
        }
        else {
            post.updatePostLikeCnt(post.getPostLikeCount() -1);
            postLikeRepository.deleteByPostAndAccount(post, account);
            return new GlobalResponseDto("게시글 좋아요 취소 완료", HttpStatus.OK.value());
        }
    }
}
