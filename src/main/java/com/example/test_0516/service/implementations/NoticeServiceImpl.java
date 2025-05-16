package com.example.test_0516.service.implementations;

import com.example.test_0516.common.ResponseMessage;
import com.example.test_0516.dto.request.NoticeCreateReqDto;
import com.example.test_0516.dto.response.NoticeRespDto;
import com.example.test_0516.dto.response.ResponseDto;
import com.example.test_0516.entity.Notice;
import com.example.test_0516.entity.User;
import com.example.test_0516.repository.NoticeRepository;
import com.example.test_0516.repository.UserRepository;
import com.example.test_0516.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<NoticeRespDto> createPost(NoticeCreateReqDto dto, String username) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }


            NoticeRespDto respDto = null;

            Notice newNotice = Notice.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .admin_id(user.getId())
                    .build();

            Notice savedNotice = noticeRepository.save(newNotice);

            respDto = NoticeRespDto.builder()
                    .title(savedNotice.getTitle())
                    .content(savedNotice.getContent())
                    .build();

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);


    }
}
