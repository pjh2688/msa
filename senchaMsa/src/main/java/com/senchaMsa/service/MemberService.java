package com.senchaMsa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senchaMsa.domain.Member;
import com.senchaMsa.domain.MemberSearch;
import com.senchaMsa.repository.MemberJpaRepository;
import com.senchaMsa.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service // 1. component scan의 대상임을 명시
@Transactional(readOnly = true) // 2. JPA는 트랜잭션 안에서 동작한다.
@RequiredArgsConstructor // 3. final이 붙은 곳에다가 생성자를 통한 주입을 해준다.(lombok)
public class MemberService {

	private final MemberJpaRepository memberJpaRepository;
	
	private final MemberRepository memberRepository;
	
	/*
	 * 4. 회원 전체 조회 서비스
	 */
	@Transactional(readOnly = true) // *참고 : 전체 클래스에 트랜잭션을 걸어주고 @Transactional(readOnly = true) -> 이걸 설정해 놓으면 JPA가 성능을 최적화 해준다.
	public List<Member> findMembersService() {
		return memberJpaRepository.findAll();
	}
	
	/*
	 * 5. 회원 생성 서비스(update에도 사용) 
	 */
	@Transactional(readOnly = false) // 참고 : 생성한 뒤 롤백 false
	public Member saveMemberService(Member member) {
		return memberJpaRepository.save(member);
	}
	
	/*
	 * 6. 회원 단건 조회 서비스
	 */
	public Optional<Member> findMemberService(Long id) {
		return memberJpaRepository.findById(id);
	}
	
	/*
	 * 7. 회원 정보 삭제 서비스 
	 */
	@Transactional(readOnly = false) // 참고 : 걸어줘야 롤백 안함.
	public void deleteMemberService(Member member) {
		memberJpaRepository.delete(member);
	}
	
	/*
	 * 8. 회원 존재 유무
	 */
	public Member findJoinedMember(String name, String password) {
		return memberJpaRepository.findByNameAndPassword(name, password);
	}
	
	/*
	 * 9. 회원 검색 
	 */
	@Transactional(readOnly = true) // *참고 : 전체 클래스에 트랜잭션을 걸어주고 @Transactional(readOnly = true) -> 이걸 설정해 놓으면 JPA가 성능을 최적화 해준다.
	public List<Member> searchMembersService(MemberSearch memberSearch) {
		return memberRepository.findAllByString(memberSearch);
	}
	
}
