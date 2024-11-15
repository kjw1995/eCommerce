package com.kjw.ecommerce.controller.dto.common;

import com.kjw.ecommerce.controller.common.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDto<T> {

    public CommonResponseDto(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private String message;

    private ResponseStatus status;

    private T data;

}
