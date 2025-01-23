package com.example.flightapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.flightapi.model.Entity.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    void deleteByUserId(Long userId);
    void deleteByUserIdAndCartItemId(Long userId, Long cartItemId);

    @Query("""
        SELECT
            c.cartItemId, 
            c.flightId, 
            f.departureCity, 
            f.arrivalCity, 
            f.price, 
            c.quantity
        FROM CartItem c 
        JOIN Flight f ON c.flightId = f.flightId 
        WHERE c.userId = :userId
    """)
    List<Object[]> findCartItemsByUserId(@Param("userId") Long userId);
}
