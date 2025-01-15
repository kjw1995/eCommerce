package com.kjw.ecommerce.controller.product;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.common.IntegrationTest;
import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.product.ProductRegisterRequestDto;
import com.kjw.ecommerce.dto.session.SessionDto;

@Transactional
@DisplayName("[통합] 테스트 - ProductController")
public class ProductControllerTest extends IntegrationTest {

	@Test
	@DisplayName("[통합] 테스트 - 상품리스트 조회 (성공)")
	void products() throws Exception {

		// given

		// when
		ResultActions actions = mockMvc.perform(
			get(CommonURL.PRODUCT)
				.accept(MediaType.APPLICATION_JSON)
		);

		// then
		actions.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").exists())
			.andExpect(jsonPath("$.msg").exists())
			.andDo(print())
			.andDo(document("Products",
				responseFields(
					fieldWithPath("data").description("응답 데이터").optional().type("List"),
					fieldWithPath("msg").description("응답 메세지").type("String")
				)
			));

	}

	@Test
	@DisplayName("[통합] 테스트 - 상품등록 (성공)")
	void registerProduct() throws Exception {

		// given
		ProductRegisterRequestDto request = ProductRegisterRequestDto.builder()
			.title("테스트 상품")
			.price(1000000L)
			.type("일반")
			.build();

		// TODO 직접 회원을 만들어서 설정하는 방법 고려
		SessionDto sessionDto = new SessionDto();
		sessionDto.setUserId("1");

		// when
		ResultActions actions = mockMvc.perform(post(CommonURL.PRODUCT)
			.sessionAttr("userSession", sessionDto)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
			.accept(MediaType.APPLICATION_JSON));

		// then
		actions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data").value(nullValue()))
			.andExpect(jsonPath("$.msg").exists())
			.andDo(print())
			.andDo(document("productRegister",
				requestFields(
					fieldWithPath("title").type(JsonFieldType.STRING).description("상품명"),
					fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품가격"),
					fieldWithPath("type").type(JsonFieldType.STRING).description("상품종류")
				),
				responseFields(
					fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터"),
					fieldWithPath("msg").type(JsonFieldType.STRING).description("응답 메세지")
				)
			));

	}

}
