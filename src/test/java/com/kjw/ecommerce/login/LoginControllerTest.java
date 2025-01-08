package com.kjw.ecommerce.login;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;
import com.kjw.ecommerce.jpa.entity.user.User;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@Transactional
@DisplayName("[통합] 테스트 - LoginController")
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("[통합] - 로그인 테스트")
	void testLogin() throws Exception {

		// given
		LoginRequestDto request = LoginRequestDto.builder()
			.id("wjddn312")
			.password("vkfksakswkd2@")
			.build();

		String requestJSON = objectMapper.writeValueAsString(request);

		// when
		ResultActions actions = mockMvc.perform(
			post(CommonURL.LOGIN).contentType(MediaType.APPLICATION_JSON)
				.content(requestJSON)
				.accept(MediaType.APPLICATION_JSON));

		// then
		actions.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.data").doesNotExist())
			.andExpect(jsonPath("$.msg").exists())
			.andDo(print())
			.andDo(document("login",
				requestFields(
					fieldWithPath("id").description("로그인 아이디").type("String"),
					fieldWithPath("password").description("비밀번호").type("String")
				),
				responseFields(
					fieldWithPath("data").description("응답 데이터").optional().type("Object"),
					fieldWithPath("msg").description("응답 메세지").type("String")
				)
			));

	}

}
