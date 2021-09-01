package com.senchaMsa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senchaMsa.domain.Product;
import com.senchaMsa.domain.ProductSearch;
import com.senchaMsa.repository.ProductJpaRepository;
import com.senchaMsa.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service // 1. component scan의 대상임을 명시
@Transactional(readOnly = true) // 2. JPA는 트랜잭션 안에서 동작한다.
@RequiredArgsConstructor // 3. final이 붙은 곳에다가 생성자를 통한 주입을 해준다.(lombok)
public class ProductService {

	private final ProductJpaRepository productJpaRepository;
	
	private final ProductRepository productRepository;
	/*
	 * 4. 상품 전체 조회 서비스
	 */
	@Transactional(readOnly = true) // *참고 : 전체 클래스에 트랜잭션을 걸어주고 @Transactional(readOnly = true) -> 이걸 설정해 놓으면 JPA가 성능을 최적화 해준다.
	public List<Product> findProductsService() {
		return productJpaRepository.findAll();
	}
	
	/*
	 * 5. 상품 정보 생성 서비스(update에도 사용) 
	 */
	@Transactional(readOnly = false) // 참고 : 생성한 뒤 롤백 false
	public Product saveProductService(Product product) {
		return productJpaRepository.save(product);
	}
	
	/*
	 * 6. 상품 정보 단건 조회 서비스
	 */
	public Optional<Product> findProductService(Long id) {
		return productJpaRepository.findById(id);
	}
	
	/*
	 * 7. 상품 정보 삭제 서비스 
	 */
	@Transactional(readOnly = false) // 참고 : 걸어줘야 롤백 안함.
	public void deleteProductService(Product product) {
		productJpaRepository.delete(product);
	}
	
	/*
	 * 8. 상품 갯수 조회 
	 */
	public Long totalCountService() {
		return productJpaRepository.count();
	}
	
	/*
	 * 9. 주문 상품 검색 
	 */
	@Transactional(readOnly = true) // *참고 : 전체 클래스에 트랜잭션을 걸어주고 @Transactional(readOnly = true) -> 이걸 설정해 놓으면 JPA가 성능을 최적화 해준다.
	public List<Product> searchProductsService(ProductSearch productSearch) {
		return productRepository.findAllByString(productSearch);
	}
	
}
