package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.post.PostAllResponseDto;
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

    private List<PostAllResponseDto> myPost;

    public MyPageResponseDto(Account account, List<PostAllResponseDto> postAllResponseDtos){
        this.accountName=account.getAccountName();

        this.myPost=postAllResponseDtos;
    }
}
