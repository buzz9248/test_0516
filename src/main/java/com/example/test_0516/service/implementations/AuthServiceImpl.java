package com.example.test_0516.service.implementations;

import com.example.test_0516.common.ResponseMessage;
import com.example.test_0516.dto.request.UserSignInReqDto;
import com.example.test_0516.dto.request.UserSignUpReqDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.dto.response.UserSignInRespDto;
import com.example.test_0516.dto.response.UserSignUpRespDto;
import com.example.test_0516.entity.User;
import com.example.test_0516.provider.JwtProvider;
import com.example.test_0516.repository.UserRepository;
import com.example.test_0516.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;


    @Override
    public ResponseDto<UserSignUpRespDto> signup(UserSignUpReqDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        if (dto.getRole().equals("ADMIN") || dto.getRole().equals("USER")) {
            String role = dto.getRole();
            UserSignUpRespDto data = null;
            User user = null;

            if (!password.equals(confirmPassword)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            if (userRepository.existsByUsername(username)) {
                return ResponseDto.setFailed(ResponseMessage.EXISTS_USERNAME);
            }

            String encodePassword = bCryptPasswordEncoder.encode(password);

            user = User.builder()
                    .username(username)
                    .password(encodePassword)
                    .role(dto.getRole())
                    .build();

            userRepository.save(user);

            data = new UserSignUpRespDto(user);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

        } else {

            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_ROLE);
        }


    }

    @Override
    public ResponseDto<UserSignInRespDto> login(UserSignInReqDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserSignInRespDto data = null;
        User user = null;

        int exprTime = jwtProvider.getExpiration();

        user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        String role = user.getRole();

        String token = jwtProvider.generateJwtToken(username, role);

        data = new UserSignInRespDto(token, user, exprTime);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
