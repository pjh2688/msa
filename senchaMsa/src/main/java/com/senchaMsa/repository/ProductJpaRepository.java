package com.senchaMsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senchaMsa.domain.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
