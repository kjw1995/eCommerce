package com.kjw.ecommerce.jpa.entity.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long id;

	@Column(name = "user_idx")
	private Long userIdx;

	@NotNull
	@Column(name = "default_address")
	private String defaultAddress;

	@Column(name = "detail_address")
	private String detailAddress;

	@Column(name = "lot_number")
	private String lotNumber;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "province")
	private String province;

	@Column(name = "district")
	private String district;

	@NotNull
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@OneToOne
	@JoinColumn(name = "user_idx", insertable = false, updatable = false)
	private User user;

}