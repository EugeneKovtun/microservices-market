package com.company.microuserservice.repository;

import com.company.microuserservice.domain.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitationRepository extends JpaRepository<Limitation, String> {
    Limitation findLimitationByType(String type);
}
