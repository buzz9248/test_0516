package com.example.test_0516.controller;

import com.example.test_0516.common.ApiMappingPattern;
import com.example.test_0516.dto.request.PostCreateReqDto;
import com.example.test_0516.dto.request.PostUpdateReqDto;
import com.example.test_0516.dto.response.PostRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST_API)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // post 생성 - 안됨
    @PostMapping
    public ResponseEntity<ResponseDto<PostRespDto>> createPost(@Valid @RequestBody PostCreateReqDto dto, @AuthenticationPrincipal String username) {
        ResponseDto<PostRespDto> response = postService.createPost(dto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    // post 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<PostRespDto>>> getAllPosts() {
        ResponseDto<List<PostRespDto>> response = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    // post 단건 조회(post_id)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PostRespDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostRespDto> response = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostRespDto>> updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateReqDto dto, @AuthenticationPrincipal String username) {
        ResponseDto<PostRespDto> response = postService.updatePost(id, dto, username);
        return ResponseEntity.ok(response);
    }




}
