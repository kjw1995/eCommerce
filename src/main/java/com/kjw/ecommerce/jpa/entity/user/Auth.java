package com.kjw.ecommerce.jpa.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "auth")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long id;

	@NotNull
	@Column(name = "user_idx")
	private long userIdx;

	@NotNull
	@Column(name = "type")
	private String type;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@OneToMany
	@JoinColumn(name = "user_idx", insertable = false, updatable = false)
	private List<User> user;

}