package com.kjw.ecommerce.jpa.repository;

import com.kjw.ecommerce.jpa.entity.JoinLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinLogRepository extends JpaRepository<JoinLog, Long> {
}
