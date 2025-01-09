package com.kjw.ecommerce.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.product.ProductListDto;
import com.kjw.ecommerce.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping(CommonURL.PAGE_PRODUCT)
	public ModelAndView product() {
		return new ModelAndView("/product/product");
	}

	@GetMapping(CommonURL.PRODUCT)
	@ResponseBody
	public ResponseEntity<CommonResponseDto<List<ProductListDto>>> getProducts() {
		return productService.getProducts();
	}

}
