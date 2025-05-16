package com.example.test_0516.service;

import com.example.test_0516.dto.request.NoticeCreateReqDto;
import com.example.test_0516.dto.response.NoticeRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import jakarta.validation.Valid;

public interface NoticeService {
    ResponseDto<NoticeRespDto> createPost(@Valid NoticeCreateReqDto dto, String username);
}
