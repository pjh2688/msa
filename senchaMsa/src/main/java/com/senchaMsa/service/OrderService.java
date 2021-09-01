package com.senchaMsa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senchaMsa.domain.Delivery;
import com.senchaMsa.domain.DeliveryStatus;
import com.senchaMsa.domain.Member;
import com.senchaMsa.domain.Order;
import com.senchaMsa.domain.OrderProduct;
import com.senchaMsa.domain.OrderSearch;
import com.senchaMsa.domain.Product;
import com.senchaMsa.repository.MemberJpaRepository;
import com.senchaMsa.repository.OrderRepository;
import com.senchaMsa.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@Service // 1. component scan의 대상임을 명시
@Transactional(readOnly = true) // 2. JPA는 트랜잭션 안에서 동작한다.
@RequiredArgsConstructor // 3. final이 붙은 곳에다가 생성자를 통한 주입을 해준다.(lombok)
public class OrderService {

	private final OrderRepository orderRepository;
	
	private final MemberJpaRepository memberJpaRepository;
	
	private final ProductJpaRepository productJpaRepository;
	
	// 4-1. 주문 생성
	@Transactional
	public Long order(Long memberId, Long productId, int count) {
		// 4-2. 엔티티 조회
		Member member = memberJpaRepository.findById(memberId).get();
		
		Product product = productJpaRepository.findById(productId).get();
		
		// 4-3. 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setPhone(member.getPhone());
		delivery.setZipcode(member.getZipcode());
		delivery.setStatus(DeliveryStatus.READY);
		
		// 4-4. 주문상품 생성
		OrderProduct orderProduct = OrderProduct.createOrderProduct(product, product.getPrice(), count);
		
		// 4-5. 주문 생성
		Order order = Order.createOrder(member, delivery, orderProduct);
		
		// 4-6. 주문 저장
		orderRepository.save(order);
	
		return order.getId();
	}
	
	// 5-1. 취소
	@Transactional
	public void cancelOrder(Long orderId) {
		// 5-2. 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		
		order.cancel();
	}
	
	// 6. 조회
	@Transactional
	public List<Order> searchOrders(OrderSearch orderSearch) {
		return orderRepository.findAllWithMemberDelivery(orderSearch);
	}
	
}
