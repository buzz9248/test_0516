package com.example.test_0516.service;

import com.example.test_0516.dto.request.UserSignInReqDto;
import com.example.test_0516.dto.request.UserSignUpReqDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.dto.response.UserSignInRespDto;
import com.example.test_0516.dto.response.UserSignUpRespDto;
import jakarta.validation.Valid;

public interface AuthService {

    ResponseDto<UserSignUpRespDto> signup(@Valid UserSignUpReqDto dto);

    ResponseDto<UserSignInRespDto> login(@Valid UserSignInReqDto dto);
}
