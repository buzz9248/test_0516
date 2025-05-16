package com.example.test_0516.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result; // 요청의 성공 여부 (true || false)
    private String message; // 요청 처리에 대한 설명 메시지
    private D data; // 실제 응답 데이터 (ex. 유저 정보, 게시글 목록 등) - 제네릭으로 어떤 타입이든 가능

    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }

}
