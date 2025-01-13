package com.kjw.ecommerce.service.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.product.ProductListDto;
import com.kjw.ecommerce.dto.product.ProductRegisterRequestDto;

public interface ProductService {

	ResponseEntity<CommonResponseDto<List<ProductListDto>>> getProducts();

	ResponseEntity<CommonResponseDto<Void>> registerProduct(ProductRegisterRequestDto requestDto);

}
