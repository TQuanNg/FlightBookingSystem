package com.example.flightapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.flightapi.model.PurchaseHistory;

public interface PurchaseRepository extends JpaRepository<PurchaseHistory, Long> {
    @Query(value = "SELECT * FROM purchase_history WHERE user_id = :userId", nativeQuery = true)
    List<PurchaseHistory> findByUserId(@Param("userId") Long userId);
}
