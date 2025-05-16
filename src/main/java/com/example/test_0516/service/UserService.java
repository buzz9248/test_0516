package com.example.test_0516.service;

import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.dto.response.UserInfoRespDto;

public interface UserService {
    ResponseDto<UserInfoRespDto> getUserInfo(String username);
}
