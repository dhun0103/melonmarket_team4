package com.clone.melonmarket.account;

import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.clone.melonmarket.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final JwtUtil jwtUtil;

    //회원가입
    @PostMapping("/auth/signup")
    public GlobalResponseDto signup(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return accountService.signup(accountRequestDto);
    }

    // 아이디 중복확인
    @PostMapping("/auth/idCheck")
    public GlobalResponseDto idCheck(@Valid @RequestBody EmailRequestDto emailRequestDto) {
        return accountService.idCheck(emailRequestDto);
    }

    // 닉네임 중복확인
    @PostMapping("/auth/nameCheck")
    public GlobalResponseDto nameCheck(@Valid @RequestBody AccountNameRequestDto accountNameRequestDto) {
        return accountService.nameCheck(accountNameRequestDto);
    }

    //로그인
    @PostMapping("/auth/login")
    public GlobalResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return accountService.login(loginRequestDto, response);
    }


    //로그아웃
    @DeleteMapping ("/api/logout")
    public GlobalResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.logout(userDetails);
    }

}
