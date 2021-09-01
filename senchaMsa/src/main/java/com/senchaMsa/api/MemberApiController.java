package com.senchaMsa.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senchaMsa.domain.Member;
import com.senchaMsa.domain.MemberSearch;
import com.senchaMsa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")  // 참고 : sencha와 데이터 주고 받기 위해선 필요.(was가 다르기 때문)
public class MemberApiController {

	private final MemberService memberService;
	
	/* 1. 회원 전체 조회  */
	@GetMapping("/members")
	public ResponseEntity<Map<String, Object>> readMembers(@ModelAttribute("searchValue") MemberSearch memberSearch) {
//		List<Member> members = memberService.findMembersService();
		List<Member> members = memberService.searchMembersService(memberSearch);
		
		Map<String, Object> result = new HashMap<String, Object>();
	
		result.put("data", members);
		result.put("code", 200);
		result.put("totalCnt", members.size());
		
		return ResponseEntity.ok(result);
	}
	
	/* 2-1. 회원 생성 */
	@PostMapping("/members")
	public ResponseEntity<Map<String, Object>> createMember(@RequestBody Member member) {  // 2-2. 폼에서 날라온 데이터를 member에서 받는다.(json 데이터이느 @RequestBody 어노테이션으로 받는다.)
		Map<String, Object> result = new HashMap<String, Object>();
		
		Member createdMember = memberService.saveMemberService(member);
		
		result.put("data", createdMember);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 3. 회원 단건 조회 by id */
	@GetMapping("/members/{id}")
	public ResponseEntity<Map<String, Object>> readMemberById(@PathVariable Long id) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Member member = memberService.findMemberService(id).get();
		
		result.put("data", member);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 4. 회원 정보 수정 */
	@PutMapping("/members/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
		Member member = memberService.findMemberService(id).get();
		
		member.setName(memberDetails.getName());
		member.setPassword(memberDetails.getPassword());
		member.setAddress(memberDetails.getAddress());
		
		Member updateMember = memberService.saveMemberService(member);
		
		return ResponseEntity.ok(updateMember);
	}
	
	/* 5. 회원 정보 삭제 */
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Map<String, Object>> deleteMemberById(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Member member = memberService.findMemberService(id).get();
		
		memberService.deleteMemberService(member);
		
		result.put("deleted", Boolean.TRUE);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
}
