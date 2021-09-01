package com.senchaMsa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.senchaMsa.domain.Member;
import com.senchaMsa.domain.MemberSearch;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;
	
	/**
	 * 1-1. 동적 쿼리 : 무식한 방법
	 */
	public List<Member> findAllByString(MemberSearch memberSearch) {
		
		// 1-2.
		String jpql = "select m from Member m";
		
		boolean isFirstCondition = true;  // 1-3. 밑에 if문이 무조건 한번은 돌아서 where을 붙이고 시작하게 하기 위해 true
		
		// 1-7. 회원 이름 검색(검색 조건이 없으면 모두 가져온다.)
		if(StringUtils.hasText(memberSearch.getSearchValue())) {
			
			if(isFirstCondition) { 
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " m.name like :searchValue";
		}
		
		TypedQuery<Member> query = em.createQuery(jpql, Member.class).setMaxResults(1000);  // 최대 1000개까지 조회
		
		if(StringUtils.hasText(memberSearch.getSearchValue())) {
			query = query.setParameter("searchValue", memberSearch.getSearchValue());
					
		}
		
		return query.getResultList();
		
	}
}
