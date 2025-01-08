package com.kjw.ecommerce.register;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.ecommerce.common.message.UserResponseMessage;
import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.register.request.AddressRequestDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@Transactional
@DisplayName("[통합] 테스트 - RegisterController")
public class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("[통합] - 회원가입 시 아이디 중복 테스트")
	void testMemberIdByMemberSearch() throws Exception {

		// given
		String testUserId = "wjddn3124";

		// when
		ResultActions actions = mockMvc.perform(
			get(CommonURL.USER + "/{userId}", testUserId).accept(MediaType.APPLICATION_JSON));

		// then
		actions.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.data").doesNotExist())
			.andExpect(jsonPath("$.msg").value(UserResponseMessage.FIND.getValue()))
			.andDo(print())
			.andDo(document("memberIdByMemberSearch",
				pathParameters(
					parameterWithName("userId").description("유저 아이디")
				),
				responseFields(
					fieldWithPath("data").description("응답 데이터").optional().type("Object"),
					fieldWithPath("msg").description("응답 메세지").type("String")
				)
			));

	}

	@Test
	@DisplayName("회원가입 테스트")
	void testRegister() throws Exception{

		// given
		AddressRequestDto addressRequestDto = AddressRequestDto.builder()
			.defaultAddress("경기도 의왕시 청계 1로 9")
			.detailAddress("607동 203호")
			.lotNumber("16010")
			.streetName("")
			.province("경기")
			.district("의왕시")
			.build();

		RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
			.userId("test01")
			.password("test01")
			.email("test01@gmail.com")
			.phoneNumber("01011112222")
			.address(addressRequestDto)
			.build();

		// when
		ResultActions actions = mockMvc.perform(
			post(CommonURL.USER).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(registerRequestDto)));

		// then
		actions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data").value(nullValue()))
			.andExpect(jsonPath("$.msg").value(UserResponseMessage.REGISTER_SUCCESS.getValue()))
			.andDo(print())
			.andDo(document("register",
				requestFields(
					fieldWithPath("userId").description("아이디"),
					fieldWithPath("password").description("비밀번호"),
					fieldWithPath("email").description("이메일"),
					fieldWithPath("phoneNumber").description("전화번호"),
					fieldWithPath("address").description("Object"),
					fieldWithPath("address.defaultAddress").description("기본 주소"),
					fieldWithPath("address.detailAddress").description("상세 주소"),
					fieldWithPath("address.lotNumber").description("지번 주소"),
					fieldWithPath("address.streetName").description("도로명 주소"),
					fieldWithPath("address.province").description("시"),
					fieldWithPath("address.district").description("군구")

				),
				responseFields(
					fieldWithPath("data").description("데이터").optional(),
					fieldWithPath("msg").description("응답 메세지")
				)
			));

	}

}
