package com.clone.melonmarket.myPage;

import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("")
    public ResponseEntity showMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails){

        Account account = userDetails.getAccount();
        return myPageService.showMyPage(account);
    }
}
