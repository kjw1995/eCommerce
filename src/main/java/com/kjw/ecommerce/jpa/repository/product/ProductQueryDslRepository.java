package com.kjw.ecommerce.jpa.repository.product;

import static com.kjw.ecommerce.jpa.entity.product.QProduct.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.config.querydsl.BaseQueryDslRepository;
import com.kjw.ecommerce.dto.product.ProductListDto;
import com.kjw.ecommerce.jpa.entity.product.Product;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class ProductQueryDslRepository extends BaseQueryDslRepository<Product, Long> {

	public ProductQueryDslRepository(EntityManager em, JPAQueryFactory queryFactory) {
		super(em, queryFactory, Product.class);
	}

	public List<ProductListDto> getProducts() {
		return queryFactory
			.select(
				Projections.fields(ProductListDto.class,
					product.title.as("title"),
					product.price.as("price"),
					product.type.as("type")
				)
			)
			.from(product)
			.fetch();
	}

}
