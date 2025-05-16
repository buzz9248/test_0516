package com.example.test_0516.service;


import com.example.test_0516.dto.request.PostCreateReqDto;
import com.example.test_0516.dto.request.PostUpdateReqDto;
import com.example.test_0516.dto.response.PostRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PostService {

    ResponseDto<PostRespDto> createPost(@Valid PostCreateReqDto dto, String username);

    ResponseDto<List<PostRespDto>> getAllPosts();

    ResponseDto<PostRespDto> getPostById(Long id);

    ResponseDto<PostRespDto> updatePost(Long id, @Valid PostUpdateReqDto dto, String username);
}
