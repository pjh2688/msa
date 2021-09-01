package com.senchaMsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senchaMsa.domain.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
