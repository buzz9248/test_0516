package com.example.test_0516.service.implementations;

import com.example.test_0516.common.ResponseMessage;
import com.example.test_0516.dto.request.PostCreateReqDto;
import com.example.test_0516.dto.request.PostUpdateReqDto;
import com.example.test_0516.dto.response.PostRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.entity.Post;
import com.example.test_0516.entity.User;
import com.example.test_0516.repository.PostRepository;
import com.example.test_0516.repository.UserRepository;
import com.example.test_0516.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public ResponseDto<PostRespDto> createPost(PostCreateReqDto dto, String username) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        PostRespDto respDto = null;

        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author_id(user.getId())
                .build();

        Post saved = postRepository.save(newPost);

        respDto = PostRespDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .author_id(saved.getAuthor_id())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    public ResponseDto<List<PostRespDto>> getAllPosts() {
        List<PostRespDto> respDto = null;

        List<Post> posts = postRepository.findAll();

        respDto = posts.stream()
                .map(post -> PostRespDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author_id(post.getAuthor_id())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    public ResponseDto<PostRespDto> getPostById(Long id) {
        PostRespDto respDto = null;

        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST));

        respDto = PostRespDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author_id(post.getAuthor_id())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    public ResponseDto<PostRespDto> updatePost(Long id, PostUpdateReqDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_USER));

        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST));

        if (post.getAuthor_id().equals(user.getId())) {
            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());

            postRepository.save(post);

            PostRespDto data = new PostRespDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor_id());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } else {

            return ResponseDto.setFailed("POST랑 AUTHOR 안맞음");
        }


    }
}
