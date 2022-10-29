package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.post.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponseDto {

    private String accountName;

    private List<PostResponseDto> myPost;

    public MyPageResponseDto(Account account, List<PostResponseDto> postResponseDtos){
        this.accountName=account.getAccountName();

        this.myPost=postResponseDtos;
    }
}
