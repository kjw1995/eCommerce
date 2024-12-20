package com.kjw.ecommerce.dto.common;

import com.kjw.ecommerce.common.status.ResponseStatus;

public record CommonResponseDto<T>(ResponseStatus status, T data, String msg) {

	public CommonResponseDto(ResponseStatus status, String msg) {
		this(status, null, msg);
	}

}
