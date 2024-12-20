package com.kjw.ecommerce.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.jpa.entity.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

}
