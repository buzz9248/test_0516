package com.example.test_0516.dto.response;

import com.example.test_0516.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSignInRespDto {
    private String token;
    private User user;
    private int exprTime;
}