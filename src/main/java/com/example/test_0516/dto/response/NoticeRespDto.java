package com.example.test_0516.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NoticeRespDto {
    private Long id;
    private String title;
    private String content;
}
