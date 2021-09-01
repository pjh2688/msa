package com.senchaMsa.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
	
	private String zipcode;
	private String address;
	private String phone;
	
	// 1. 기본 생성자 protected로 막아논다.
	protected Address() {
	}
	
	// 2. setter를 안만들고 생성자로 초기화하게끔 설계
	public Address(String zipcode, String address, String phone) {
		this.zipcode = zipcode;
		this.address = address;
		this.phone = phone;
	}
}
