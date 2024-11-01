package com.kjw.ecommerce.jpa.repository;

import com.kjw.ecommerce.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    

}
