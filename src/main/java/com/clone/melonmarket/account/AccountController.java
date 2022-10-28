package com.clone.melonmarket.account;

import com.clone.melonmarket.global.GlobalResponseDto;
import com.clone.melonmarket.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AccountController {

    private final AccountService accountService;

    private final JwtUtil jwtUtil;

    //회원가입
    @PostMapping("/signup")
    public GlobalResponseDto signup(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return accountService.signup(accountRequestDto);
    }

    // 아이디 중복확인
    @PostMapping("/idCheck")
    public GlobalResponseDto idCheck(@Valid @RequestBody EmailRequestDto emailRequestDto) {
        return accountService.idCheck(emailRequestDto);
    }

    // 닉네임 중복확인
    @PostMapping("/nameCheck")
    public GlobalResponseDto nameCheck(@Valid @RequestBody AccountNameRequestDto accountNameRequestDto) {
        return accountService.nameCheck(accountNameRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public GlobalResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return accountService.login(loginRequestDto, response);
    }
}
