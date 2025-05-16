package com.example.test_0516.controller;

import com.example.test_0516.common.ApiMappingPattern;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.dto.response.UserInfoRespDto;
import com.example.test_0516.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.USER_INFO_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<UserInfoRespDto>> getUserInfo(@AuthenticationPrincipal String username) {
        ResponseDto<UserInfoRespDto> response = userService.getUserInfo(username);
        return ResponseEntity.ok(response);
    }


}
