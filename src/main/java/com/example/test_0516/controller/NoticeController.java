package com.example.test_0516.controller;

import com.example.test_0516.common.ApiMappingPattern;
import com.example.test_0516.dto.request.NoticeCreateReqDto;
import com.example.test_0516.dto.response.NoticeRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.NOTICE_API)
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ResponseDto<NoticeRespDto>> createNotice(@Valid @RequestBody NoticeCreateReqDto dto, @AuthenticationPrincipal String username) {
        ResponseDto<NoticeRespDto> response = noticeService.createPost(dto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
