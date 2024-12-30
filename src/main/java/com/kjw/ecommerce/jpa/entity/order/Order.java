package com.kjw.ecommerce.jpa.entity.order;

import java.time.LocalDateTime;

import com.kjw.ecommerce.jpa.entity.product.Product;
import com.kjw.ecommerce.jpa.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long id;

	@Column(name = "user_idx")
	private Long userIdx;

	@Column(name = "product_idx")
	private Long productIdx;

	@NotNull
	@Column(name = "quantity")
	private Integer quantity;

	@NotNull
	@Column(name = "total_price")
	private Long totalPrice;

	@NotNull
	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@ManyToOne
	@JoinColumn(name = "user_idx", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_idx", insertable = false, updatable = false)
	private Product product;

	@OneToOne(mappedBy = "order")
	private OrderDetail orderDetail;

}