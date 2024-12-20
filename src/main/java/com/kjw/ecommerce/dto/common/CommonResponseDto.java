package com.kjw.ecommerce.dto.common;

import com.kjw.ecommerce.common.status.ResponseStatus;

public record CommonResponseDto<T>(ResponseStatus status, T data, String msg) {

	public CommonResponseDto(ResponseStatus status) {
		this(status, null, null);
	}

	public CommonResponseDto(T data) {
		this(null, data, null);
	}

	public CommonResponseDto(String msg) {
		this(null, null, msg);
	}

	public CommonResponseDto(ResponseStatus status, String msg) {
		this(status, null, msg);
	}

	public CommonResponseDto(ResponseStatus status, T data) {
		this(status, data, null);
	}

	public CommonResponseDto(T data, String msg) {
		this(null, data, msg);
	}

	public static CommonResponseDto success() {
		return new CommonResponseDto<>(ResponseStatus.SUCCESS, null, "");
	}

	public static CommonResponseDto successWithMsg(String msg) {
		return new CommonResponseDto<>(ResponseStatus.SUCCESS, null, msg);
	}

	public static CommonResponseDto failed() {
		return new CommonResponseDto<>(ResponseStatus.FAILED, null, "");
	}

	public static CommonResponseDto failedWithMsg(String msg) {
		return new CommonResponseDto<>(ResponseStatus.FAILED, null, msg);
	}

	public static CommonResponseDto error() {
		return new CommonResponseDto<>(ResponseStatus.ERROR, null, "");
	}

	public static CommonResponseDto errorWithMsg(String msg) {
		return new CommonResponseDto<>(ResponseStatus.ERROR, null, msg);
	}

}
