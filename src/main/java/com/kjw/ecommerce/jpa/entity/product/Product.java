package com.kjw.ecommerce.jpa.entity.product;

import java.time.LocalDateTime;
import java.util.List;

import com.kjw.ecommerce.jpa.entity.order.Order;
import com.kjw.ecommerce.jpa.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long id;

	@Column(name = "user_idx")
	private Long userIdx;

	@NotNull
	@Column(name = "title")
	private String title;

	@NotNull
	@Column(name = "price")
	private Long price;

	@Column(name = "type")
	private String type;

	@NotNull
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@ManyToOne
	@JoinColumn(name = "product", insertable = false, updatable = false)
	private User user;

	@OneToMany(mappedBy = "product")
	private List<Order> order;

}