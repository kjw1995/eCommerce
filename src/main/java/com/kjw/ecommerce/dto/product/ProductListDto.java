package com.kjw.ecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductListDto {

	/**
	 * 상품명
	 */
	private String title;

	/**
	 * 상품가격
	 */
	private Long price;

	/**
	 * 상품종류
	 */
	private String type;

}
