package com.senchaMsa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	private String password;
	
	private String zipcode;
	
	private String address;
	
	private String phone;
	
	@JsonIgnore  // 양방향 연관관계에서는 한쪽을 @JsonIgnore 꼭 달아줘야 한다.
	@OneToMany(mappedBy = "member")  // Order 테이블에 있는 member 필드를 지정.(읽기 전용)
	private List<Order> orders = new ArrayList<>();
}
