package com.senchaMsa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.senchaMsa.domain.Order;
import com.senchaMsa.domain.OrderSearch;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	/**
	 * 3. 엔티티를 DTO로 변환 - 페치 조인으로 최적화 (검색 포함) -> 2021-08-30
	 */
	public List<Order> findAllWithMemberDelivery(OrderSearch orderSearch) {
		
		String jpql = "select o from Order o" + 
						" join fetch o.member m" + 
						" join fetch o.delivery d" + 
						" join fetch o.orderProducts op" +
						" join fetch op.product p";
		
		boolean isFirstCondition = true;
		
		// 1-7. 회원 이름 검색(검색 조건이 없으면 모두 가져온다.)
		if(StringUtils.hasText(orderSearch.getSearchValue())) {
					
			if(isFirstCondition) { 
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " m.name like :searchValue";
		}
		
		TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000);  // 최대 1000개까지 조회
		
		if(StringUtils.hasText(orderSearch.getSearchValue())) {
			query = query.setParameter("searchValue", orderSearch.getSearchValue());
					
		}
		
		return query.getResultList();
		
	}
	
	
}
