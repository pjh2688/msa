package com.senchaMsa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_product_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@JsonIgnore  // 양방향 연관관계에서는 한쪽을 @JsonIgnore 꼭 달아줘야 한다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice;  // 주문 가격
	
	private int count;  // 주문 수량
	
	/* static 생성 메소드 */
	public static OrderProduct createOrderProduct(Product product, int orderPrice, int count) {
		OrderProduct orderProduct = new OrderProduct();
		
		orderProduct.setProduct(product);
		orderProduct.setOrderPrice(orderPrice);
		orderProduct.setCount(count);
		
		// 주문을 하면 기본적으로 item에서 재고를 주문 수량만큼 감소 시켜줘야 한다.
		product.removeStock(count);
		return orderProduct;
	}
	
	/* 비즈니스 로직 */
	/**
	 *  주문 취소
	 */
	public void cancel() {
		getProduct().addStock(count);  // 주문 취소가 되면  OrderItem에서는 getter로 item을 가져온 뒤 주문 수량(count)을 다시 채워넣는다.
	}
	
	/* 조회 로직 */
	/**
	 *  주문 상품들의 총 가격(주문 가격 X 주문 수량)
	 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

}
