package com.example.test_0516.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateReqDto {

    @NotBlank(message = "제목은 필수")
    private String title;

    @NotBlank(message = "내용도 필수")
    private String content;
}
