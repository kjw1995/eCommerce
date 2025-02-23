package com.kjw.ecommerce.jpa.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.jpa.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(String id);

}
