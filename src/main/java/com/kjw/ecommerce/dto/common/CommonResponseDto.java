package com.kjw.ecommerce.dto.common;

public record CommonResponseDto<T>(T data, String msg) {

	public CommonResponseDto(T data) {
		this(data, null);
	}

	public CommonResponseDto(String msg) {
		this(null, msg);
	}

}
