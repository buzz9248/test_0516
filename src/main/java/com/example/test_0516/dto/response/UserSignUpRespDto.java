package com.example.test_0516.dto.response;

import com.example.test_0516.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignUpRespDto {
    User user;
}
