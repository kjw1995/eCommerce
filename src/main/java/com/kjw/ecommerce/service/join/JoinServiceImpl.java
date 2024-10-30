package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.jpa.repository.JoinLogRepository;
import com.kjw.ecommerce.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final UserRepository userRepository;

    private final JoinLogRepository joinLogRepository;

    @Override
    public JoinResponseDto join(JoinRequestDto requestDto) {
        return null;
    }

}
