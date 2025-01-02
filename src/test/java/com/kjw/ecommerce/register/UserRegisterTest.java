package com.kjw.ecommerce.register;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.controller.register.RegisterController;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.service.register.RegisterService;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = RegisterController.class)
public class UserRegisterTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RegisterService registerService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("회원 아이디 조회 - 성공")
	void testGetMemberIdByMemberSearch() throws Exception {

		// given
		String userId = "wjddn3124";
		when(registerService.memberIdByMemberSearch(userId)).thenReturn(
			ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto("회원ID 사용 가능")));

		// when & then
		mockMvc.perform(RestDocumentationRequestBuilders.get(CommonURL.USER + "/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isAccepted())
			.andDo(document("memberIdByMemberSearch",
				pathParameters(
					parameterWithName("userId").description("유저 아이디")
				),
				responseFields(
					fieldWithPath("data").description("data").optional(),
					fieldWithPath("msg").description("응답 본문")
				)
			));

	}

}
