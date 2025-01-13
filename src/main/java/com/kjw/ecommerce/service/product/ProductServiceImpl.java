package com.kjw.ecommerce.service.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.common.exception.DataListFetchException;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.product.ProductListDto;
import com.kjw.ecommerce.dto.product.ProductRegisterRequestDto;
import com.kjw.ecommerce.dto.session.SessionDto;
import com.kjw.ecommerce.jpa.entity.product.Product;
import com.kjw.ecommerce.jpa.repository.product.ProductQueryDslRepository;
import com.kjw.ecommerce.jpa.repository.product.ProductRepository;
import com.kjw.ecommerce.util.SessionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final ProductQueryDslRepository productQueryDslRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CommonResponseDto<List<ProductListDto>>> getProducts() {

		List<ProductListDto> responseData = Optional.of(productQueryDslRepository.getProducts())
			.orElseThrow(() -> new DataListFetchException("상품 정보 조회 오류"));

		return ResponseEntity.ok(new CommonResponseDto<>(responseData));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<CommonResponseDto<Void>> registerProduct(ProductRegisterRequestDto requestDto) {

		try {

			SessionDto session = SessionUtils.getUserSession();

			Product product = Product.builder()
				.title(requestDto.getTitle())
				.price(requestDto.getPrice())
				.type(requestDto.getType())
				.createdAt(LocalDateTime.now())
				.userIdx(session.getUserIdx())
				.build();

			productRepository.save(product);

		} catch (Exception e) {
			log.error("상품 등록 에러", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("상품 등록 실패"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto<>("상품 등록 성공"));
	}

}
