package com.senchaMsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senchaMsa.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
	
	Member findByNameAndPassword(String name, String password);
}
