package com.kjw.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kjw.ecommerce.common.exception.DataListFetchException;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.product.ProductListDto;
import com.kjw.ecommerce.jpa.repository.product.ProductQueryDslRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductQueryDslRepository productQueryDslRepository;

	@Override
	public ResponseEntity<CommonResponseDto<List<ProductListDto>>> getProducts() {

		List<ProductListDto> responseData = Optional.of(productQueryDslRepository.getProducts())
			.orElseThrow(() -> new DataListFetchException("상품 정보 조회 오류"));

		return ResponseEntity.ok(new CommonResponseDto<>(responseData));
	}

}
