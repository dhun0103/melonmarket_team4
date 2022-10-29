package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {
    public ResponseEntity showMyPage(Account account) {

        // 내가 쓴 게시글 조회
        List<Post> postList = postRepository.findPostsByAccount(account);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post foundPost : postList) {

            postResponseDtos.add(new PostResponseDto(foundPost));
        }

        return new ResponseEntity(
                new MyPageResponseDto(account, postResponseDtos),
                HttpStatus.OK
        );
    }
}
