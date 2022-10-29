package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.comment.Comment;
import com.clone.melonmarket.comment.CommentResponseDto;
import com.clone.melonmarket.post.Post;
import com.clone.melonmarket.post.PostRepository;
import com.clone.melonmarket.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final PostRepository postRepository;

    public ResponseEntity showMyPage(Account account) {

        // 내가 쓴 게시글 조회
        List<Post> postList = postRepository.findPostsByAccount(account);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post foundPost : postList) {
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            for (Comment comment : foundPost.getComments()) {
                commentResponseDtos.add(new CommentResponseDto(comment));
            }
            postResponseDtos.add(new PostResponseDto(foundPost, commentResponseDtos));
        }

        return new ResponseEntity(
                new MyPageResponseDto(account, postResponseDtos),
                HttpStatus.OK
        );
    }
}
