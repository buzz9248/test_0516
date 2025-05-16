package com.example.test_0516.controller;

import com.example.test_0516.common.ApiMappingPattern;
import com.example.test_0516.dto.request.UserSignInReqDto;
import com.example.test_0516.dto.request.UserSignUpReqDto;
import com.example.test_0516.dto.response.UserSignInRespDto;
import com.example.test_0516.dto.response.UserSignUpRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final String POST_SIGN_UP = "/signup";
    private static final String POST_SIGN_IN = "/login";

    @PostMapping(POST_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpRespDto>> signup(@Valid @RequestBody UserSignUpReqDto dto) {
        ResponseDto<UserSignUpRespDto> response = authService.signup(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(POST_SIGN_IN)
    public ResponseEntity<ResponseDto<UserSignInRespDto>> login(@Valid @RequestBody UserSignInReqDto dto) {
        ResponseDto<UserSignInRespDto> response = authService.login(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}