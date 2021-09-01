package com.senchaMsa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.senchaMsa.domain.Product;
import com.senchaMsa.domain.ProductSearch;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
	
	private final EntityManager em;
	
	/**
	 * 1-1. 동적 쿼리 : 무식한 방법
	 */
	public List<Product> findAllByString(ProductSearch productSearch) {
		
		// 1-2.
		String jpql = "select p from Product p";
		
		boolean isFirstCondition = true;  // 1-3. 밑에 if문이 무조건 한번은 돌아서 where을 붙이고 시작하게 하기 위해 true
		
		// 1-7. 회원 이름 검색(검색 조건이 없으면 모두 가져온다.)
		if(StringUtils.hasText(productSearch.getSearchValue())) {
			
			if(isFirstCondition) { 
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " p.name like :searchValue";
		}
		
		TypedQuery<Product> query = em.createQuery(jpql, Product.class).setMaxResults(1000);  // 최대 1000개까지 조회
		
		if(StringUtils.hasText(productSearch.getSearchValue())) {
			query = query.setParameter("searchValue", productSearch.getSearchValue());
		}
		
		return query.getResultList();
		
	}
}
