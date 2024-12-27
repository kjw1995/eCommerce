package com.kjw.ecommerce.jpa.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.ecommerce.jpa.entity.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
