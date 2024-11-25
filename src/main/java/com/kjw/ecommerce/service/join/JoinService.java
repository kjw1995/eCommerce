package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.request.InquiryMemberRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.response.InquiryMemberResponseDto;

public interface JoinService {

    CommonResponseDto<JoinResponseDto> join(JoinRequestDto requestDto);

    CommonResponseDto<InquiryMemberResponseDto> memberIdByMemberSearch(InquiryMemberRequestDto requestDto);

}
