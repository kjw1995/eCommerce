package com.kjw.ecommerce.jpa.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import com.kjw.ecommerce.jpa.entity.order.Order;
import com.kjw.ecommerce.jpa.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx", nullable = false)
	private Long idx;

	@NotNull
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "is_active")
	private String isActive;

	@NotNull
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@OneToOne(mappedBy = "user")
	private Address address;

	@OneToMany(mappedBy = "user")
	private List<Auth> auth;

	@OneToMany(mappedBy = "user")
	private List<Product> product;

	@OneToMany(mappedBy = "user")
	private List<Order> order;

}