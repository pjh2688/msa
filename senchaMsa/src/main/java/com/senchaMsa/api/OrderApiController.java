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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senchaMsa.domain.Order;
import com.senchaMsa.domain.OrderSearch;
import com.senchaMsa.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderApiController {

	private final OrderService orderService;
	
	/* 1-1. 주문 정보 조회  */  
	@GetMapping("/orders")
	public ResponseEntity<Map<String, Object>> readOrders(@ModelAttribute("searchValue") OrderSearch orderSearch) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<Order> orders = orderService.searchOrders(orderSearch);
		
		result.put("data", orders);
		result.put("code", 200);
		result.put("totalCnt", orders.size());
		
		return ResponseEntity.ok(result);
	}
	
	/* 2-1. 주문 생성 */ 
	@PostMapping("/orders")
	public ResponseEntity<Map<String, Object>> createOrder(@RequestParam("memberId") Long memberId, 
														   @RequestParam("productId") Long productId,
														   @RequestParam("count") int count) {  // 2-2. 
		Map<String, Object> result = new HashMap<String, Object>();
		
		Long orderId = orderService.order(memberId, productId, count);
		
		result.put("orderId", orderId);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 3. 주문 단건 조회 by id */
	@GetMapping("/orders/{id}")
	public ResponseEntity<Map<String, Object>> readOrderById(@PathVariable Long id) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 4. 주문 정보 수정 */
	@PutMapping("/orders/{id}")
	public ResponseEntity<Map<String, Object>> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 5. 주문 정보 삭제 */
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Map<String, Object>> deleteMemberById(@PathVariable Long id) {
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("deleted", Boolean.TRUE);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
}
