package com.dornor.spring_test.Repository;

import com.dornor.spring_test.Entity.SSOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface SSORepository extends JpaRepository<SSOUser, LocalDateTime>{
}
