package com.kjw.ecommerce.jpa.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.jpa.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
