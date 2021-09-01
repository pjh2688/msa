package com.senchaMsa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.senchaMsa.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stock;
	
/* 비즈니스 로직 */
	
	/**
	 *  stock 증가
	 */
	public void addStock(int quantity) {
		this.stock += quantity;
	}
	
	/**
	 *  stock 감소
	 */
	public void removeStock(int quantity) {
		
		int restStock = this.stock - quantity;  // 현재 재고 수량에서 주문 수량을 빼면 남은 수량이 나온다.
	
		if(restStock < 0) {  // 남은 수량이 0보다 작으면
			throw new NotEnoughStockException("need more stock");
		}
		this.stock = restStock;
	}
}
