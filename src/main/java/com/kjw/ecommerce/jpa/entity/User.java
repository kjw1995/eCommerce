package com.kjw.ecommerce.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx", nullable = false)
	private Long idx;

	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "detail_address")
	private String detailAddress;

	@Column(name = "sido")
	private String sido;

	@Column(name = "sigungu")
	private String sigungu;

}