package com.kjw.ecommerce.jpa.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.jpa.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
