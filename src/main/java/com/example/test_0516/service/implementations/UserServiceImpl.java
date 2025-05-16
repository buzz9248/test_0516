package com.example.test_0516.service.implementations;

import com.example.test_0516.common.ResponseMessage;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.dto.response.UserInfoRespDto;
import com.example.test_0516.entity.User;
import com.example.test_0516.repository.UserRepository;
import com.example.test_0516.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDto<UserInfoRespDto> getUserInfo(String username) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        UserInfoRespDto respDto = new UserInfoRespDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }
}
